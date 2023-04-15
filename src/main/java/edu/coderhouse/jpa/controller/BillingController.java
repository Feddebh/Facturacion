package edu.coderhouse.jpa.controller;

import edu.coderhouse.jpa.models.dto.PurchaseRequest;
import edu.coderhouse.jpa.models.entities.Invoice;
import edu.coderhouse.jpa.service.BillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/invoices")
public class BillingController {

  @Autowired private BillingService billingService;

  @PostMapping
  public ResponseEntity<?> createInvoice(@RequestBody @Valid PurchaseRequest purchaseRequest) {
    Invoice createdInvoice = billingService.createInvoice(purchaseRequest);
    if (createdInvoice == null) {
      return ResponseEntity.badRequest().body(null);
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(createdInvoice);
  }

  @GetMapping("/{clientId}")
  public ResponseEntity<Iterable<Invoice>> getInvoicesByClientId(@PathVariable Long clientId) {
    Iterable<Invoice> invoices = billingService.getInvoicesByClientId(clientId);
    if (invoices == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(invoices);
  }
}

/*

RECIBO UN PURCHASEREQUEST CON EL ID DEL CLIENTE Y UNA LISTA DE productDTO,

{
  "client_id": 123456,
  "invoice_details":[
    {
      "product_id":123,
      "amount":2

    },
    {
      "product_id":133,
      "amount":3
    }

  ]


}

- VALIDAR QUE EL CLIENTE EXISTA EN LA BDD
- ITERAR LOS PRODUCTDTO Y VERIFICAR QUE TODOS LOS PRODUCTID ESTEN EN BDD Y TENGAN EL STOCK REQUERIDO.
- AGREGAR GOOGLE JAVA FORMAT AL PROYECTO.
 */
