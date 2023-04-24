package edu.coderhouse.jpa.exceptions;

import edu.coderhouse.jpa.models.dto.ErrorResponse;

import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({BillingException.class})
  public ResponseEntity<ErrorResponse> billingException(BillingException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatusCode(
       e.getStatusCode().value());
    errorResponse.setMessage(e.getMessage());
    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> generalException(Exception e) {
    ErrorResponse errorResponse = new ErrorResponse();
    errorResponse.setStatusCode(400);
    errorResponse.setMessage(e.getMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
  }
}
