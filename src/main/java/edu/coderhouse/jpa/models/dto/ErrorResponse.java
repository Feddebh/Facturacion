package edu.coderhouse.jpa.models.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.Serializable;
import lombok.Data;

@Data
public class ErrorResponse implements Serializable {
  @JsonProperty("status_code")
  private int statusCode;

  private String message;
}
