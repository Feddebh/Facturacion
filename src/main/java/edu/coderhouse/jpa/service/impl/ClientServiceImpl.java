package edu.coderhouse.jpa.service.impl;

import edu.coderhouse.jpa.exceptions.BillingException;
import edu.coderhouse.jpa.mappers.ClientMapper;
import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import edu.coderhouse.jpa.repository.ClientRepository;
import edu.coderhouse.jpa.service.ClientService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;

@Slf4j
@Service
public class ClientServiceImpl implements ClientService {

  @Autowired private ClientRepository clientRepository;
  @Autowired private ClientMapper clientMapper;

  @Override
  public Client findByDocNumber(String docNumber) throws BillingException {
    return this.clientRepository.findByDocNumber(docNumber)
            .orElseThrow(() -> new BillingException("RESOURCE.NOT.FOUND"));
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
  public Client getClientById(Long clientId) throws BillingException{
    if (clientId <= 0){
      throw new BillingException("EL ID INGRESADO NO ES VALIDO");
    }
    return this.clientRepository.findById(clientId).
            orElseThrow(() -> new BillingException("El ID especificado (aun) no existe."));
  }

  @Override
  public Client updateClient(Long clientId, ClientDTO updatedClientDTO) throws BillingException {
    Client existingClient = clientRepository.findById(clientId).orElseThrow(() -> new BillingException
              ("No se encuentra el cliente con ID: " + clientId));
      clientMapper.updateClientFromDTO(updatedClientDTO, existingClient);
        return clientRepository.save(existingClient);
      }
  }

