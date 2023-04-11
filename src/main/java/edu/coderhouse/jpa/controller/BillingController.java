package edu.coderhouse.jpa.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.service.impl.BillingServiceImpl;

@RestController
@RequestMapping("/v1/billing")
public class BillingController {

    @Autowired
    private BillingServiceImpl billingServiceImpl;

    @PostMapping("/invoices")
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        return billingServiceImpl.createInvoice(invoice);
    }

    @GetMapping("/invoices/{clientId}")
    public ResponseEntity<Iterable<Invoice>> getInvoicesByClientId(@PathVariable Long clientId) {
        Iterable<Invoice> invoices = billingServiceImpl.getInvoicesByClientId(clientId);
        if (invoices == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(invoices);
    }
}