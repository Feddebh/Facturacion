package edu.coderhouse.jpa.exceptions;

//Esta excepción podría ocurrir si se llama a algún método con un parámetro nulo o si se intenta acceder a un objeto que
// es nulo.

public class NullParameterException extends NullPointerException {

    public NullParameterException(String mensaje) {
        super(mensaje);
    }

}
