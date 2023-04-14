package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.entities.Invoice;

public interface BillingService {


    Invoice createInvoice(Invoice invoice);

    Iterable<Invoice> getInvoicesByClientId(Long clientId);
}
