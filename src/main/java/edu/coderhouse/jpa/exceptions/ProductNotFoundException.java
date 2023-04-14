package edu.coderhouse.jpa.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String mensaje){
        super(mensaje);
    }
}
