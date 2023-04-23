package edu.coderhouse.jpa.exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class BillingException extends RuntimeException {

  private final HttpStatus statusCode;

  public BillingException(String msg, HttpStatus statusCode) {
    super(msg);
    this.statusCode = statusCode;
  }
}
