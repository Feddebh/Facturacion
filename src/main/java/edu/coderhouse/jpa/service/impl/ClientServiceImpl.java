package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.exceptions.ClientNotFoundException;
import edu.coderhouse.jpa.exceptions.NullParameterException;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.service.ClientService;
import java.util.List;
import java.util.Optional;
import edu.coderhouse.jpa.validations.ClientValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

  @Autowired private ClientRepository clientRepository;
  @Autowired private ClientValidator clientValidator;

  @Override
  public Client findByDocnumber(String docNumber) throws BillingException {
    this.clientValidator.validateDocNumber(docNumber);

    return this.clientRepository.findByDocNumber(docNumber)
            .orElseThrow(() -> new BillingException("RESOURCE.NOT.FOUND"));
  }
  @Override
  public Client addClient(Client client) throws BillingException {
    log.info("NUEVO CLIENTE " + client);
    this.clientValidator.validate(client);

    Optional<Client> clientInDb = this.clientRepository.findByDocNumber(client.getDocNumber());
    if (clientInDb.isPresent()) {
      throw new BillingException("RESOURCE.ALREADY.EXISTS");
    }
      return clientRepository.save(client);
    }


  @Override
  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }

  @Override
  public Client getClientById(Long clientId) {
    Optional<Client> optionalClient = clientRepository.findById(clientId);
    if (optionalClient.isPresent()) {
      return optionalClient.get();
    } else {

      throw new ClientNotFoundException("No se encuentra el cliente con ID: " + clientId);
    }
  }

  @Override
  public Client updateCLient(Long clientId, Client updatedClient) {

    if (clientId == null) {
      throw new NullParameterException("El parametro Id del cliente no puede ser null");
    } else if (updatedClient == null) {
      throw new NullParameterException("El parámetro updatedClient no puede ser null");
    } else {
      Client existingClient = clientRepository.findById(clientId).orElse(null);
      if (existingClient != null) {
        existingClient.setName(updatedClient.getName());
        existingClient.setLastName(updatedClient.getLastName());
        existingClient.setDocNumber(updatedClient.getDocNumber());
        return clientRepository.save(existingClient);
      }
      return null;
    }
  }
}
