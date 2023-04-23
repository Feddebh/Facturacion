package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class ClientDTO implements Serializable {
  @JsonProperty("client_id")
  private Long clientId;

  private String name;
  private String lastName;
  private String docNumber;
}
