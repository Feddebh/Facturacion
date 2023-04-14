package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.models.entities.Client;
import java.util.List;

public interface ClientService {
  Client addClient(Client candidateClient);

  void deleteClient(Long clientId);

  List<Client> getAllClients();

  Client getClientById(Long clientId);

  Client updateCLient(Long clientId, Client updatedClient);
}
