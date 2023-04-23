package edu.coderhouse.jpa.mappers;

import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {
  ClientDTO clientToClientDTO(Client client);

  Client clientDtoToClient(ClientDTO clientDTO);

  void updateClientFromDTO(ClientDTO clientDTO, @MappingTarget Client client);
}
