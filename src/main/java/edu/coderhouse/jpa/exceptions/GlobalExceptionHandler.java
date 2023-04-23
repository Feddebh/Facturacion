package edu.coderhouse.jpa.exceptions;

import edu.coderhouse.jpa.models.dto.ErrorResponse;
import java.util.Locale;

import lombok.RequiredArgsConstructor;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

  private final MessageSource messageSource;

  @ExceptionHandler({BillingException.class, Exception.class, ConstraintViolationException.class})
  public ResponseEntity<ErrorResponse> billingException(BillingException e) {
    ErrorResponse errorResponse = new ErrorResponse();
    System.out.println("LLEGUE HASTA ACA");
    errorResponse.setStatusCode(
       e.getStatusCode().value());

    errorResponse.setMessage(e.getMessage());

    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
  }
}
