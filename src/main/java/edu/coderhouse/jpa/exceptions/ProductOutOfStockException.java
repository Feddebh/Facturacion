package edu.coderhouse.jpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ProductOutOfStockException extends Exception {

  public ProductOutOfStockException(String message) {
    super(message);
  }
}
