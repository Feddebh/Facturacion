package edu.coderhouse.jpa.models.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ErrorResponse implements Serializable {
  @JsonProperty("status_code")
  private int statusCode;
  private String message;
}
