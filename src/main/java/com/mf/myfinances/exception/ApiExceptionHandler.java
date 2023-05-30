package com.mf.myfinances.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

  @ExceptionHandler(value = { CashFlowTypeNotFoundException.class })
  public ResponseEntity<Object> handleCashFlowTypeNotFoundException(CashFlowTypeNotFoundException e) {
    ApiException apiException = new ApiException(
            e.getMessage(),
            HttpStatus.NOT_FOUND,
            "CFT01",
            ZonedDateTime.now()
    );
    return new ResponseEntity<>(apiException, HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(value = { CashFlowTypeNotNullOrEmptyException.class })
  public ResponseEntity<Object> handleCashFlowTypeNotNullException(CashFlowTypeNotNullOrEmptyException e) {
    ApiException apiException = new ApiException(
            e.getMessage(),
            HttpStatus.CONFLICT,
            "CFT02",
            ZonedDateTime.now()
    );
    return new ResponseEntity<>(apiException, HttpStatus.CONFLICT);
  }
}
