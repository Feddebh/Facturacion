package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.dto.PurchaseRequest;
import edu.coderhouse.jpa.models.entities.Invoice;

public interface BillingService {

  Invoice createInvoice(PurchaseRequest purchaseRequest) throws BillingException;

  Iterable<Invoice> getInvoicesByClientId(Long clientId);
}
