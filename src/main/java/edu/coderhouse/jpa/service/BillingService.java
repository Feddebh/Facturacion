package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.dto.PurchaseRequest;
import edu.coderhouse.jpa.models.entities.Invoice;
import java.util.List;

public interface BillingService {

  Invoice createInvoice(PurchaseRequest purchaseRequest);

  List<Invoice> getInvoicesByClientId(Long clientId);
}
