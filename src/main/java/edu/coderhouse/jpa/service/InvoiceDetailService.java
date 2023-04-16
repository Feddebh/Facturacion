package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.models.entities.InvoiceDetail;

import java.math.BigDecimal;

public interface InvoiceDetailService {
    BigDecimal calculateDetailsTotal(InvoiceDetail detail, Invoice invoice, Integer amount);

    InvoiceDetail saveInvoiceDetail(InvoiceDetail detail);
}
