package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.dto.PurchaseRequest;
import edu.coderhouse.jpa.models.entities.Invoice;

public interface BillingService {

  Invoice createInvoice(PurchaseRequest purchaseRequest);

  Iterable<Invoice> getInvoicesByClientId(Long clientId);
}
