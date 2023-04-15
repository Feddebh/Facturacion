package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.entities.InvoiceDetail;

import java.math.BigDecimal;

public interface InvoiceDetailService {

   public BigDecimal calculateDetailsTotal(InvoiceDetail detail);

}
