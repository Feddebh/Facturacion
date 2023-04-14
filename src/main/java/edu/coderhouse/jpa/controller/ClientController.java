package edu.coderhouse.jpa.controller;

import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.service.ClientService;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/client")
public class ClientController {

  @Autowired private ClientService clientservice;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<Client> addClient(@Valid @RequestBody Client candidateClient) {
    return ResponseEntity.ok(clientservice.addClient(candidateClient));
  }

  @PutMapping(value = "/{clientId}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Client> updateClient(
      @PathVariable Long clientId, @Valid @RequestBody Client updatedClient) {
    Client updated = clientservice.updateCLient(clientId, updatedClient);
    return ResponseEntity.ok(updated);
  }

  @DeleteMapping(value = "/{clientId}")
  public ResponseEntity<Void> deleteClient(@PathVariable Long clientId) {
    clientservice.deleteClient(clientId);
    return ResponseEntity.ok().build();
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<Client>> getAllClients() {
    return ResponseEntity.ok(clientservice.getAllClients());
  }

  // Obtener un cliente por ID.

  @GetMapping(value = "/{clientId}", produces = "application/json")
  public ResponseEntity<Client> getClientById(@NotNull @PathVariable Long clientId) {
    Client client = clientservice.getClientById(clientId);
    if (client == null) {
      return ResponseEntity.notFound().build();
    }
    return ResponseEntity.ok(client);
  }
}
