package edu.coderhouse.jpa.controllers;

import edu.coderhouse.jpa.mappers.ClientMapper;
import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.service.ClientService;
import java.util.List;
import javax.persistence.Column;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/v1/client")
@RequiredArgsConstructor
@Validated
public class ClientController {

  private final ClientService clientService;
  private final ClientMapper clientMapper;

  @PostMapping(consumes = "application/json", produces = "application/json")
  public ResponseEntity<ClientDTO> addClient(@Valid @RequestBody ClientDTO candidateClientDto){
    Client newClient = this.clientService.addClient(candidateClientDto);
    return ResponseEntity.status(HttpStatus.CREATED)
        .header("location", "/" + newClient.getId())
        .body(candidateClientDto);
  }
  @PutMapping(value = "/{clientId}", consumes = "application/json", produces = "application/json")
  public ResponseEntity<ClientDTO> updateClient(
      @PathVariable Long clientId, @Valid @RequestBody ClientDTO updatedClientDTO){
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
  public ResponseEntity<ClientDTO> getClientById(@NotNull @PathVariable Long clientId){
    Client client = this.clientService.getClientById(clientId);
    return ResponseEntity.ok(this.clientMapper.clientToClientDTO(client));
  }

  @GetMapping(value = "/dni/{docNumber}", produces = "application/json")
  public ResponseEntity<Client>
  getClientByDocNumber(@PathVariable("docNumber")
                       @Size(min = 2, max = 11, message = "El DNI debe tener entre 2 y 11 caracteres.")
                       @Pattern(regexp = "^[0-9]+$", message = "Solo se permiten numeros del 0 al 9") String docNumber){
    return ResponseEntity.ok(clientService.getClientByDocNumber(docNumber));
  }


}
