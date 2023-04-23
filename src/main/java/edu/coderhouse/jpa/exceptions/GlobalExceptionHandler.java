package edu.coderhouse.jpa.exceptions;

import edu.coderhouse.jpa.models.dto.ErrorResponse;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Locale;

@RestControllerAdvice
public class GlobalExceptionHandler {
  private ErrorResponse errorResponse;
  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler({BillingException.class, Exception.class})
  public ResponseEntity<ErrorResponse> billingException(BillingException e) {
    this.errorResponse = new ErrorResponse();

    errorResponse.setStatusCode(Integer.parseInt(
            this.messageSource.getMessage(e.getMessage() + ".STATUSCODE", null, Locale.getDefault())));
    errorResponse.setStatus(
            this.messageSource.getMessage(e.getMessage() + ".STATUS", null, Locale.getDefault()));
    errorResponse.setMessage(
            this.messageSource.getMessage(e.getMessage() + ".MESSAGE", null, Locale.getDefault()));

    return new ResponseEntity<>(errorResponse, HttpStatus.valueOf(errorResponse.getStatusCode()));
  }
}
