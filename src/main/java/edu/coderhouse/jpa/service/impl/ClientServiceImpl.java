package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.mappers.ClientMapper;
import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.service.ClientService;
import java.util.List;
import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor

public class ClientServiceImpl implements ClientService {

  private final ClientRepository clientRepository;
  private final ClientMapper clientMapper;

  @Override
  public Client findByDocNumber(String docNumber) throws BillingException {
    return this.clientRepository
        .findByDocNumber(docNumber).orElseThrow(() ->
                    new BillingException("NO SE ENCUENTRA EL CLIENTE CON EL DNI INGRESADO", HttpStatus.NOT_FOUND));
  }

  @Override
  public Client addClient(@Valid ClientDTO candidateClientDTO) {
    log.info("NUEVO CLIENTE: " + candidateClientDTO);
    Client newClient = clientMapper.clientDtoToClient(candidateClientDTO);
    return clientRepository.save(newClient);
  } // OK

  @Override
  public List<Client> getAllClients() {
    return clientRepository.findAll();
  }

  @Override
  public Client getClientById(Long clientId) throws BillingException {
    if (clientId <= 0) {
      throw new BillingException("EL ID INGRESADO NO ES VALIDO", HttpStatus.BAD_REQUEST);
    }
    return this.clientRepository
        .findById(clientId)
        .orElseThrow(() -> new BillingException("El ID especificado (aun) no existe.", HttpStatus.NOT_FOUND));
  }

  @Override
  public Client updateClient(Long clientId, ClientDTO updatedClientDTO) throws BillingException {
    Client existingClient =
        clientRepository
            .findById(clientId)
            .orElseThrow(
                () -> new BillingException("No se encuentra el cliente con ID: " + clientId, HttpStatus.NOT_FOUND));
    clientMapper.updateClientFromDTO(updatedClientDTO, existingClient);
    return clientRepository.save(existingClient);
  }
}
