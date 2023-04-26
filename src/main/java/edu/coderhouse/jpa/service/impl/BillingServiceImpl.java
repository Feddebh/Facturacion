package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.dto.BillingDetail;
import edu.coderhouse.jpa.models.dto.BillingRequest;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.models.entities.InvoiceDetail;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.repository.InvoiceDetailRepository;
import edu.coderhouse.jpa.repository.InvoiceRepository;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.BillingService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

  private final InvoiceRepository invoiceRepository;

  private final ClientRepository clientRepository;

  private final InvoiceDetailRepository invoiceDetailRepository;

  private final ProductRepository productRepository;

  @Override
  public Invoice createInvoice(BillingRequest billingRequest) {

    // Obtener el cliente de la base de datos por su id
    Long clientId = billingRequest.getClientId();
    Client client =
        clientRepository
            .findClientByIdAndActiveTrue(billingRequest.getClientId())
            .orElseThrow(
                () ->
                    new BillingException(
                        "NO SE PUDO ENCONTRAR EL CLIENTE CON ID: " + clientId,
                        HttpStatus.NOT_FOUND));

    // Configurar la fecha de creación de la factura
    Invoice invoice = new Invoice();
    invoice.setCreatedAt(
        LocalDateTime.now().atZone(ZoneId.of("America/Buenos_Aires")).toLocalDateTime());
    invoice.setClient(client);

    // Inicializar acumuladores globales
    BigDecimal invoiceTotal = new BigDecimal(0);
    List<InvoiceDetail> invoiceDetails = new ArrayList<>();

    // Iterar indicaciones de facturación
    for (BillingDetail billingDetail : billingRequest.getBillingDetails()) {

      Long productId = billingDetail.getProductId();
      Product product =
          productRepository
              .findById(productId)
              .orElseThrow(
                  () ->
                      new BillingException(
                          "NO SE PUDO ENCONTRAR EL PRODUCTO CON ID: " + productId,
                          HttpStatus.NOT_FOUND));

      // Crear detalle de factura
      InvoiceDetail detail = new InvoiceDetail();
      detail.setInvoice(invoice);
      detail.setAmount(billingDetail.getAmount());
      detail.setAppliedPrice(product.getPrice());
      detail.setProduct(product);
      detail.setSubtotal(detail.getAppliedPrice().multiply(new BigDecimal(detail.getAmount())));
      invoiceDetailRepository.save(detail);

      // Actualizar Stock
      int newStock = product.getStock() - billingDetail.getAmount();
      if (newStock < 0) {
        throw new BillingException(
            "EL AMOUNT DEL PRODUCTO CON ID: " + productId + " NO ESTÁ DISPONIBLE EN STOCK.",
            HttpStatus.BAD_REQUEST);
      }
      product.setStock(newStock);
      productRepository.save(product);

      // Incrementar acumuladores
      invoiceTotal = invoiceTotal.add(detail.getSubtotal());
      invoiceDetails.add(detail);
    }

    // Asignar acumuladores a la factura
    invoice.setTotal(invoiceTotal);
    invoice.setInvoiceDetails(invoiceDetails);

    // Guardar la factura y retornar
    return invoiceRepository.save(invoice);
  }

  @Override
  public List<Invoice> getInvoicesByClientId(Long clientId) {
    Client client =
        clientRepository
            .findById(clientId)
            .orElseThrow(
                () ->
                    new BillingException(
                        "No se encontro el cliente con el ID especificado. " + clientId,
                        HttpStatus.NOT_FOUND));
    return invoiceRepository.findByClient(client);
  }

  @Override
  public Invoice getInvoiceById(Long id) {
    return invoiceRepository
        .findById(id)
        .orElseThrow(
            () ->
                new BillingException(
                    "No se encontró la factura con el ID especificado. " + id,
                    HttpStatus.NOT_FOUND));
  }
}
