package edu.coderhouse.jpa.controllers;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.dto.PurchaseRequest;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;
import javax.validation.Valid;

@RestController
@RequestMapping("/v1/billing")
public class BillingController {

  @Autowired private BillingService billingService;

  @PostMapping(consumes = "application/json", produces = "application/json")
  @Transactional
  public ResponseEntity<Invoice> createInvoice(@RequestBody @Valid PurchaseRequest purchaseRequest) throws BillingException {
     return ResponseEntity.status(HttpStatus.CREATED).body(billingService.createInvoice(purchaseRequest));
  }

  @GetMapping("/{clientId}")
  public ResponseEntity<Iterable<Invoice>> getInvoicesByClientId(@PathVariable Long clientId) {

    return ResponseEntity.ok(billingService.getInvoicesByClientId(clientId));
  }
}
