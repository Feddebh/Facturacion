package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.mappers.ClientMapper;
import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.service.ClientService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  public Client registerClient(ClientDTO candidateClientDTO) {
    log.info("NUEVO CLIENTE: " + candidateClientDTO);
    Client newClient = clientMapper.clientDtoToClient(candidateClientDTO);
    Client savedClient = clientRepository.save(newClient);
    savedClient.setId(savedClient.getId());
    return savedClient;
  }

  @Override
  public Client updateExistingClient(Long clientId, ClientDTO updatedClientDTO) {
    Client existingClient =
        clientRepository
            .findById(clientId)
            .orElseThrow(
                () ->
                    new BillingException(
                        "No se encuentra el cliente con ID: " + clientId, HttpStatus.NOT_FOUND));
    clientMapper.updateClientFromDTO(updatedClientDTO, existingClient);
    return clientRepository.save(existingClient);
  }

  @Override
  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }

  @Override
  public Client getClientById(Long clientId) {
    return this.clientRepository
        .findById(clientId)
        .orElseThrow(
            () ->
                new BillingException(
                    "No se encuentra el cliente con ID: " + clientId, HttpStatus.NOT_FOUND));
  }

  @Override
  public Client getClientByDocNumber(String docNumber) {
    return this.clientRepository
        .findByDocNumber(docNumber)
        .orElseThrow(
            () ->
                new BillingException(
                    "NO SE ENCUENTRA EL CLIENTE CON EL DNI INGRESADO", HttpStatus.NOT_FOUND));
  }
}
