package edu.coderhouse.jpa.exceptions;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class BillingException extends RuntimeException {

  private final HttpStatus statusCode;

  public BillingException(String msg, HttpStatus statusCode) {
    super(msg);
    this.statusCode = statusCode;
  }
}
