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

    errorResponse.setStatusCode(
        Integer.parseInt(
            this.messageSource.getMessage(
                e.getMessage() + ".STATUSCODE", null, Locale.getDefault())));
    errorResponse.setStatus(
        this.messageSource.getMessage(e.getMessage() + ".STATUS", null, Locale.getDefault()));
    errorResponse.setMessage(
        this.messageSource.getMessage(e.getMessage() + ".MESSAGE", null, Locale.getDefault()));

    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
  }
}
