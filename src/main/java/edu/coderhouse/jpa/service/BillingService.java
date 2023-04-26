package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.dto.BillingRequest;
import edu.coderhouse.jpa.models.entities.Invoice;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import org.springframework.validation.annotation.Validated;

@Validated
public interface BillingService {

  Invoice createInvoice(@Valid BillingRequest billingRequest);

  List<Invoice> getInvoicesByClientId(@Positive Long clientId);

  Invoice getInvoiceById(@NotNull @Positive Long id);
}
