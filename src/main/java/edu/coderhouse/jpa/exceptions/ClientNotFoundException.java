package edu.coderhouse.jpa.exceptions;

// Esta excepción se lanza cuando no se encuentra un cliente con el ID proporcionado en el método
// getClientById().

public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException(String mensaje) {

    super(mensaje);
  }
}
