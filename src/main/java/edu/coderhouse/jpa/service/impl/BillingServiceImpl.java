package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.dto.ProductAmountDTO;
import edu.coderhouse.jpa.models.dto.PurchaseRequest;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.models.entities.InvoiceDetail;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.repository.InvoiceRepository;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.BillingService;
import edu.coderhouse.jpa.service.InvoiceDetailService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BillingServiceImpl implements BillingService {

  private final InvoiceRepository invoiceRepository;

  private final ClientRepository clientRepository;

  private final InvoiceDetailService invoiceDetailService;

  private final ProductRepository productRepository;

  @Override
  public Invoice createInvoice(PurchaseRequest purchaseRequest) throws BillingException {

    // Obtener el cliente de la base de datos por su id
    Long clientId = purchaseRequest.getClientId();
    Client client =
        clientRepository
            .findById(clientId)
            .orElseThrow(() -> new BillingException("NO SE PUDO ENCONTRAR EL CLIENTE", HttpStatus.NOT_FOUND));

    // Verificar si el cliente existe
    // Configurar la fecha de creación de la factura
    Invoice invoice = new Invoice();
    invoice.setCreatedAt(LocalDateTime.now());
    invoice.setClient(client);

    Invoice savedInvoice = invoiceRepository.save(invoice);

    BigDecimal invoiceTotal = new BigDecimal(0);

    List<InvoiceDetail> savedDetails = new ArrayList<>();

    for (ProductAmountDTO productAmountDTO : purchaseRequest.getPurchaseDetails()) {

      Long productId = productAmountDTO.getProductId();
      Product product =
          productRepository
              .findById(productId)
              .orElseThrow(
                  () -> new BillingException("EL PRODUCTO: " + productId + " NO FUE ENCONTRADO", HttpStatus.NOT_FOUND));

      InvoiceDetail detail = new InvoiceDetail();
      detail.setInvoice(savedInvoice);
      detail.setAmount(productAmountDTO.getAmount());
      detail.setAppliedPrice(product.getPrice());
      detail.setProduct(product);
      detail.setSubtotal(detail.getAppliedPrice().multiply(new BigDecimal(detail.getAmount())));

      invoiceDetailService.saveInvoiceDetail(detail);
      invoiceTotal = invoiceTotal.add(detail.getSubtotal());
      savedDetails.add(detail);
      product.setStock(product.getStock() - productAmountDTO.getAmount());

      productRepository.save(product);
    }

    savedInvoice.setTotal(invoiceTotal);
    savedInvoice.setInvoiceDetails(savedDetails);

    // Guardar la factura en la base de datos
    return invoiceRepository.save(savedInvoice);
  }

  @Override
  public List<Invoice> getInvoicesByClientId(Long clientId) {

    // Obtener el cliente de la base de datos por su id
    Client client = clientRepository.findById(clientId).orElse(null);

    // Verificar si el cliente existe
    if (client == null) {
      return null;
    }

    // Obtener las facturas del cliente
    List<Invoice> invoices = invoiceRepository.findByClient(client);

    // Retornar las facturas del cliente en el cuerpo de la respuesta
    return ResponseEntity.ok(invoices).getBody();
  }
}
