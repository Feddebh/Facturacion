package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.models.entities.InvoiceDetail;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.repository.InvoiceRepository;
import edu.coderhouse.jpa.repository.ProductRepository;
import edu.coderhouse.jpa.service.BillingService;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BillingServiceImpl implements BillingService {

  @Autowired private InvoiceRepository invoiceRepository;

  @Autowired private ClientRepository clientRepository;

  @Autowired private ProductRepository productRepository;

  @Autowired private InvoiceDetailServiceImpl invoiceDetailServiceImpl; // Inyectar la nueva clase


  @Override
  public Invoice createInvoice(Invoice invoice) {

    // Obtener el cliente de la base de datos por su id
    Long clientId = invoice.getClient().getId();
    Client client = clientRepository.findById(clientId).orElse(null);

    // Verificar si el cliente existe
    if (client == null) {
      return null; //No debe retornar null
    }

    // Configurar la fecha de creación de la factura
    invoice.setCreatedAt(LocalDateTime.now());

// Calcular el total de la factura utilizando el nuevo servicio
    BigDecimal total = BigDecimal.ZERO;
    for (InvoiceDetail detail : invoice.getInvoiceDetails()) {
      BigDecimal detailTotal = invoiceDetailServiceImpl.calculateDetailTotal(detail);
      detail.setPrice(detailTotal);
      total = total.add(detailTotal);
    }
    invoice.setTotal(total);

    // Guardar la factura en la base de datos
    return invoiceRepository.save(invoice);
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
