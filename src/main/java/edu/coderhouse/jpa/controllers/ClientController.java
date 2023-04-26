package edu.coderhouse.jpa.controllers;

import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * User interface for Client management.
 *
 * @author Federico Bohle
 * @version 1.0.0
 * @since 1.0.0
 * @see <a href="https://github.com/Feddebh/Facturacion/tree/test">Facturacion</a>
 */
@Slf4j
@RestController
@RequestMapping("/v1/client")
@RequiredArgsConstructor
public class ClientController {

  private final ClientService clientService;

  /**
   * Registers a new client in the system based on the provided client data. The client data is
   * passed as a JSON object in the request body.
   *
   * @param candidateClientDto {@link ClientDTO} the DTO object representing the client to be
   *     registered.
   * @return a ResponseEntity object containing the newly created {@link Client} entity and a 201
   *     Created HTTP status code.
   * @apiNote The dto fields must be valid. Please check the validations at {@link ClientDTO}
   */
  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<Client> registerClient(@RequestBody ClientDTO candidateClientDto) {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(clientService.registerClient(candidateClientDto));
  }

  /**
   * Updates an existing client with the specified ID using the information provided in the request
   * body.
   *
   * @param clientId the ID of the client to update.
   * @param updatedClientDTO {@link ClientDTO} the DTO object representing the client to be updated.
   * @throws edu.coderhouse.jpa.exceptions.BillingException if no client is found with the specified
   *     id.
   * @return a ResponseEntity object containing the updated {@link Client} entity and a 200 OK HTTP
   *     status code.
   */
  @PutMapping(value = "/{clientId}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<Client> updateExistingClient(
      @PathVariable(name = "clientId") Long clientId, @RequestBody ClientDTO updatedClientDTO) {
    return ResponseEntity.ok(clientService.updateExistingClient(clientId, updatedClientDTO));
  }

  /**
   * Retrieve a List with all clients.
   *
   * @return ResponseEntity containing a list of clients in the response body, with
   *     "application/json" as the content type.
   * @apiNote This endpoint can be used to fetch a complete list of all clients in the system.
   */
  @GetMapping(produces = "application/json")
  public ResponseEntity<List<Client>> getAllClients() {
    return ResponseEntity.ok(clientService.getAllClients());
  }

  /**
   * Retrieves a client by ID.
   *
   * @param clientId the ID of the client to retrieve
   * @throws edu.coderhouse.jpa.exceptions.BillingException if no client is found with the specified
   *     ID
   * @return a ResponseEntity containing the retrieved client and an HTTP status code indicating the
   *     success of the operation
   */
  @GetMapping(value = "/{clientId}", produces = "application/json")
  public ResponseEntity<Client> getClientById(@PathVariable(name = "clientId") Long clientId) {
    return ResponseEntity.ok(clientService.getClientById(clientId));
  }

  /**
   * Retrieves the client with the given identification card number.
   *
   * @param docNumber a string representing the document number of the client to be retrieved
   * @return a ResponseEntity with a Client object in the body if the client is found, or a 404
   *     status code if not found
   */
  @GetMapping(value = "/dni/{docNumber}", produces = "application/json")
  public ResponseEntity<Client> getClientByDocNumber(
      @PathVariable(name = "docNumber") String docNumber) {
    return ResponseEntity.ok(clientService.getClientByDocNumber(docNumber));
  }
}
