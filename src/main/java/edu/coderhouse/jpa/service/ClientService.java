package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public interface ClientService {
  Client registerClient(@Valid ClientDTO client);

  Client updateExistingClient(@Positive Long clientId, @Valid ClientDTO updatedClientDTO);

  List<Client> getAllClients();

  Client getClientById(@Positive Long clientId);

  Client getClientByDocNumber(
      @Size(min = 2, max = 11, message = "El DNI debe tener entre 2 y 11 caracteres.")
          @Pattern(regexp = "^[0-9]+$", message = "Solo se permiten numeros del 0 al 9")
          String docNumber);
}
