package edu.coderhouse.jpa.exceptions;

public class ProductOutOfStockException extends RuntimeException {

  public ProductOutOfStockException(String message) {
    super(message);
  }
}
