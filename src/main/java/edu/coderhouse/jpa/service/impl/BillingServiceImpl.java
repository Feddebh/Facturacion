package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.NullParameterException;
import edu.coderhouse.jpa.exceptions.ProductOutOfStockException;
import edu.coderhouse.jpa.models.dto.ProductDTO;
import edu.coderhouse.jpa.models.dto.PurchaseRequest;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.models.entities.InvoiceDetail;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.repository.InvoiceRepository;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.BillingService;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import edu.coderhouse.jpa.service.InvoiceDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {

  @Autowired private InvoiceRepository invoiceRepository;

  @Autowired private ClientRepository clientRepository;

  @Autowired private InvoiceDetailService invoiceDetailService;

  @Autowired private ProductRepository productRepository;


  @Override
  public Invoice createInvoice(PurchaseRequest purchaseRequest) {

    // Obtener el cliente de la base de datos por su id
    Long clientId = Long.parseLong(purchaseRequest.getClientId());
    Client client = clientRepository.findById(clientId).orElseThrow(() -> new NullParameterException
            ("El parámetro candidateClient no puede ser nulo"));

    // Verificar si el cliente existe
      // Configurar la fecha de creación de la factura
      Invoice invoice = new Invoice();
      invoice.setCreatedAt(LocalDateTime.now());
      invoice.setClient(client);

// Calcular el total de la factura utilizando el nuevo servicio
      BigDecimal total = BigDecimal.ZERO;
      Invoice savedInvoice = invoiceRepository.save(invoice);

      for (ProductDTO productDTO : purchaseRequest.getPurchaseDetails()){

        Product product = productRepository.findById(productDTO.getProductId()).orElseThrow(() ->
                new NullParameterException("El parámetro candidateClient no puede ser nulo"));
        if (product.getStock() < productDTO.getAmount()){
          throw new ProductOutOfStockException("No hay suficiente stock");
        }
      }

      BigDecimal  invoiceTotal = new BigDecimal(0);
      for(ProductDTO productDTO : purchaseRequest.getPurchaseDetails()){

        Product product = productRepository.findById(productDTO.getProductId()).orElseThrow(() ->
                new NullParameterException("El parámetro candidateClient no puede ser nulo"));
        InvoiceDetail detail = new InvoiceDetail();
        detail.setInvoice(savedInvoice);
        detail.setAmount(productDTO.getAmount());
        detail.setPrice(product.getPrice());
        detail.setProduct(product);
        detail.setSubtotal(detail.getPrice().multiply(new BigDecimal(detail.getAmount())));

        invoiceDetailService.saveInvoiceDetail(detail);
        invoiceTotal = invoiceTotal.add(detail.getSubtotal());

        System.out.println("EL SUBTOTAL ES: " + invoiceTotal);
      }

      savedInvoice.setTotal(invoiceTotal);



      // Guardar la factura en la base de datos
      return savedInvoice;
    }

  @Override
  public Iterable<Invoice> getInvoicesByClientId(Long clientId) {

    // Obtener el cliente de la base de datos por su id
    Client client = clientRepository.findById(clientId).orElse(null);

    // Verificar si el cliente existe
    if (client == null) {
      return null;
    }

    // Obtener las facturas del cliente
    Iterable<Invoice> invoices = invoiceRepository.findByClient(client);

    // Retornar las facturas del cliente en el cuerpo de la respuesta
    return ResponseEntity.ok(invoices).getBody();
  }
}
