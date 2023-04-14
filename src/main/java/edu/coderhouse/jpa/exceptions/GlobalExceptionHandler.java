package edu.coderhouse.jpa.exceptions;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ClientNotFoundException.class)
    public ResponseEntity<?> ClientNotFoundException(Exception e){
        JsonObject response = new JsonObject();
        response.addProperty("message", e.getMessage());
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(response), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<?> ProductNotFoundException(Exception e){
        JsonObject response = new JsonObject();
        response.addProperty("message", e.getMessage());
        Gson gson = new Gson();
        return new ResponseEntity<>(gson.toJson(response), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NullParameterException.class)
    public ResponseEntity<String> handleNullPointerException(NullPointerException ex) {
        return ResponseEntity.badRequest().body
                ("Se ha producido una excepción de tipo NullPointerException: " + ex.getMessage());
    }

    @ExceptionHandler(ProductOutOfStockException.class)
    public ResponseEntity<String> handleProductOutOfStockException(ProductOutOfStockException ex) {
        // Puedes personalizar la respuesta de error según tus necesidades
        String error = "Error: " + ex.getMessage();
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

}
