package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import java.util.List;
import javax.validation.Valid;

public interface ClientService {
  Client getClientByDocNumber(String docNumber);

  Client addClient(@Valid ClientDTO client);

  List<Client> getAllClients();

  Client getClientById(Long clientId);

  Client updateClient(Long clientId, ClientDTO updatedClientDTO);
}
