package edu.coderhouse.jpa.controllers;

import edu.coderhouse.jpa.models.dto.BillingRequest;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.service.BillingService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * User interface for Billing management.
 *
 * @author Federico Bohle
 * @version 1.0.0
 * @since 1.0.0
 * @see <a href="https://github.com/Feddebh/Facturacion/tree/test">Facturacion</a>
 */
@RestController
@RequestMapping("/v1/billing")
@RequiredArgsConstructor
@Validated
public class BillingController {

  private final BillingService billingService;

  /**
   * Creates an invoice with the provided data in the billing request {@link BillingRequest}
   *
   * @param billingRequest a DTO with the clientId and a list of billing details for creating the
   *     invoice.
   * @return a {@link ResponseEntity} containing the created invoice and HTTP status code 201
   *     (CREATED).
   * @throws edu.coderhouse.jpa.exceptions.BillingException if there is an error creating the
   *     invoice.
   * @apiNote The newly created invoice can be used for various purposes such as accounting,
   *     financial reporting, or payment processing.
   */
  @PostMapping(consumes = "application/json", produces = "application/json")
  @Transactional
  public ResponseEntity<Invoice> createInvoice(@RequestBody BillingRequest billingRequest) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(billingService.createInvoice(billingRequest));
  }

  /**
   * Retrieves a list of invoices associated with a client with the provided client ID.
   *
   * @param clientId the ID of the client whose invoices are to be retrieved. Must be a positive
   *     non-null Long value.
   * @return a {@link ResponseEntity} object containing a list of {@link Invoice} objects associated
   *     with the client ID and HTTP status code 200 (OK).
   * @throws edu.coderhouse.jpa.exceptions.BillingException if the client ID is not valid.
   * @apiNote Useful to fetch a list of invoices associated with a specific client, for accounting
   *     and billing purposes.
   */
  @GetMapping(value = "/invoices/client/{clientId}", produces = "application/json")
  public ResponseEntity<List<Invoice>> getInvoicesByClientId(@PathVariable Long clientId) {
    return ResponseEntity.ok(billingService.getInvoicesByClientId(clientId));
  }

  /**
   * Retrieves an invoice with the provided invoice ID.
   *
   * @param invoiceId the ID of the invoice to be retrieved. Must be a positive non-null Long value.
   * @return a {@link ResponseEntity} object containing the {@link Invoice} object associated with
   *     the invoice ID and HTTP status code 200 (OK).
   * @throws edu.coderhouse.jpa.exceptions.BillingException if the invoice ID is not valid.
   * @apiNote This endpoint retrieves an invoice with its purchase details for the provided invoice
   *     ID. The invoice ID is provided as a path variable in the endpoint URL. The endpoint
   *     produces data in JSON format. This endpoint can be used to fetch an invoice by a previously
   *     created invoice ID by providing it in the URL.
   */
  @GetMapping(value = "/invoices/{invoiceId}", produces = "application/json")
  public ResponseEntity<Invoice> getInvoiceByInvoiceId(@PathVariable Long invoiceId) {
    return ResponseEntity.ok(billingService.getInvoiceById(invoiceId));
  }
}
