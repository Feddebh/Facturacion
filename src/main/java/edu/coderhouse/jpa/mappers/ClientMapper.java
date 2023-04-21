package edu.coderhouse.jpa.mappers;
import edu.coderhouse.jpa.models.dto.ClientDTO;
import edu.coderhouse.jpa.models.entities.Client;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ClientMapper {

    ClientDTO clientToClientDTO(Client client);
    Client clientDtoToClient(ClientDTO clientDTO);
}