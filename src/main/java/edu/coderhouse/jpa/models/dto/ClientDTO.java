package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class ClientDTO implements Serializable {
    @JsonProperty("client_id")
    private Long clientId;
    private String name;
    private String lastname;
    private String docNumber;
}
