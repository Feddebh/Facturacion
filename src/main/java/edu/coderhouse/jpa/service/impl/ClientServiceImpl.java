package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.service.ClientService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

  @Autowired private ClientRepository clientRepository;

  @Override
  public Client findByDocnumber(String docNumber) throws BillingException {
    return this.clientRepository.findByDocNumber(docNumber)
            .orElseThrow(() -> new BillingException("RESOURCE.NOT.FOUND"));
  }
  @Override
  public Client addClient(Client client) {
    log.info("NUEVO CLIENTE: " + client);
      return clientRepository.save(client);
    }

  @Override
  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }

  @Override
  public Client getClientById(Long clientId) throws BillingException{
    if (clientId <= 0){
      throw new BillingException("EL ID INGRESADO NO ES VALIDO");
    }
    return this.clientRepository.findById(clientId).
            orElseThrow(() -> new BillingException("El ID especificado (aun) no existe."));
  }

  @Override
  public Client updateClient(Long clientId, Client updatedClient) throws BillingException {

      Client existingClient = clientRepository.findById(clientId).orElseThrow(() -> new BillingException
              ("No se encuentra el cliente con ID: " + clientId));

      existingClient.setName(updatedClient.getName());
      existingClient.setLastName(updatedClient.getLastName());
      existingClient.setDocNumber(updatedClient.getDocNumber());
        return clientRepository.save(existingClient);
      }
  }

