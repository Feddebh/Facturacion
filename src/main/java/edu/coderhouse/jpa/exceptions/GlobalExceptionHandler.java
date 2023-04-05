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

}
