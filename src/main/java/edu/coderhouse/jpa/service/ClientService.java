package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.entities.Client;
import java.util.List;

public interface ClientService {
  Client findByDocnumber(String docnumber) throws BillingException;

  Client addClient(Client candidateClient) throws BillingException;

  List<Client> getAllClients();

  Client getClientById(Long clientId) throws BillingException;

  Client updatedCLient(Long clientId, Client updatedClient) throws BillingException;
}
