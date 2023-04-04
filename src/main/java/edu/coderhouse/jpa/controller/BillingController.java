package edu.coderhouse.jpa.controller;

import edu.coderhouse.jpa.models.dto.PurchaseRequest;
import edu.coderhouse.jpa.models.dto.PurchaseResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping ("/v1/billing")

public class BillingController {

    @PostMapping(value = "/purchase", consumes = "application/json", produces = "application/json")
    public ResponseEntity<PurchaseResponse> purchase (@RequestBody PurchaseRequest request) {
    return null;
    }
}
