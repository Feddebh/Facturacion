package edu.coderhouse.jpa.controller;

import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.models.entities.Product;
import edu.coderhouse.jpa.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientservice;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Client> addClient(@RequestBody Client candidateClient){
        return ResponseEntity.ok(clientservice.addClient(candidateClient));
    }

}
