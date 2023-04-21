package edu.coderhouse.jpa.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// Esta excepción se lanza cuando no se encuentra un cliente con el ID proporcionado en el método
// getClientById().
@ResponseStatus(HttpStatus.NOT_FOUND)
public class ClientNotFoundException extends RuntimeException {
  public ClientNotFoundException(String mensaje) {

    super(mensaje);
  }
}
