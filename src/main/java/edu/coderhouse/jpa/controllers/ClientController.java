package edu.coderhouse.jpa.controllers;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.mappers.ClientMapper;
import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.service.ClientService;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/v1/client")
public class ClientController {

  @Autowired private ClientService clientService;

  @Autowired private ClientMapper clientMapper;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO candidateClientDTO) throws BillingException {
    Client addedClient = this.clientService.addClient(candidateClientDTO);
    return ResponseEntity.status(HttpStatus.CREATED)
            .header("location", "/" + addedClient.getId())
            .body(candidateClientDTO);
  } //OK

  @PutMapping(value = "/{clientId}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<ClientDTO> updateClient(
    @PathVariable Long clientId, @Valid @RequestBody ClientDTO updatedClientDTO) throws BillingException {
      Client updatedClient = this.clientService.updateClient(clientId, updatedClientDTO);
      ClientDTO updatedClientResponse = this.clientMapper.clientToClientDTO(updatedClient);
      return ResponseEntity.ok(updatedClientResponse);
  }

  @GetMapping(produces = "application/json")
  public ResponseEntity<List<Client>> getAllClients() {

    return ResponseEntity.ok(this.clientService.getAllClients());
  }

  // Obtener un cliente por ID.

  @GetMapping(value = "/{clientId}", produces = "application/json")
  public ResponseEntity<ClientDTO> getClientById(@NotNull @PathVariable Long clientId) throws BillingException {
    Client client = this.clientService.getClientById(clientId);
    return ResponseEntity.ok(this.clientMapper.clientToClientDTO(client));
  }
  }
