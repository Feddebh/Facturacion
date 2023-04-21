package edu.coderhouse.jpa.controller;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.exceptions.ClientNotFoundException;
import edu.coderhouse.jpa.mappers.ClientMapper;
import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.service.ClientService;

import java.net.URI;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
@Slf4j
@RestController
@RequestMapping("/v1/client")
public class ClientController {

  @Autowired private ClientService clientService;
  @Autowired
  private ClientMapper clientMapper;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO clientDTO) throws BillingException {
    log.info("NUEVO CLIENTE: " + clientDTO);
    Client client = this.clientMapper.clientDtoToClient(clientDTO);
    Client addedClient = this.clientService.addClient(client);
    return ResponseEntity.created(URI.create("/" + addedClient.getId()))
            .body(this.clientMapper.clientToClientDTO(addedClient));
  }

  @PutMapping(value = "/{clientId}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<ClientDTO> updateClient(
      @PathVariable Long clientId, @Valid @RequestBody ClientDTO updatedClientDTO) {
    log.info("CLIENTE ACTUALIZADO " + updatedClientDTO);
    Client updatedClient = this.clientMapper.clientDtoToClient(updatedClientDTO);
    Client updated = this.clientService.updateCLient(clientId, updatedClient);
    return ResponseEntity.ok(this.clientMapper.clientToClientDTO(updated));
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
