package edu.coderhouse.jpa.models.dto;

import java.io.Serializable;
import lombok.Data;

@Data
public class ErrorResponse implements Serializable {
  private int statusCode;
  private String status;
  private String message;
}
