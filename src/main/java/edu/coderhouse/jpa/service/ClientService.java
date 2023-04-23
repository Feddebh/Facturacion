package edu.coderhouse.jpa.service;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;

import javax.validation.Valid;
import java.util.List;

public interface ClientService {
  Client findByDocNumber(String docNumber) throws BillingException;

  Client addClient(@Valid ClientDTO client) throws BillingException;


  List<Client> getAllClients();

  Client getClientById(Long clientId) throws BillingException;

  Client updateClient(Long clientId, ClientDTO updatedClientDTO) throws BillingException;
}
