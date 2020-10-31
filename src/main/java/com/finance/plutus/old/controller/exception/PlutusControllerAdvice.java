package com.finance.plutus.old.controller.exception;

import com.finance.plutus.old.controller.payload.ErrorResponse;
import com.finance.plutus.old.exception.PlutusException;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

/** Plutus Created by catalin on 7/1/2020 */
@Log4j2
@RestControllerAdvice
public class PlutusControllerAdvice {

  @ExceptionHandler(value = PlutusException.class)
  public ResponseEntity<ErrorResponse> handleException(PlutusException exception) {
    log.error(getClass().getSimpleName(), exception);
    return buildResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
  }

  @ExceptionHandler(value = BindException.class)
  public ResponseEntity<ErrorResponse> handleException(BindException exception) {
    log.error(getClass().getSimpleName(), exception);
    return buildResponse(collectErrors(exception));
  }

  @ExceptionHandler(value = MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleException(MethodArgumentNotValidException exception) {
    log.error(getClass().getSimpleName(), exception);
    return buildResponse(collectErrors(exception));
  }

  @ExceptionHandler(value = HttpMessageNotReadableException.class)
  public ResponseEntity<ErrorResponse> handleException(HttpMessageNotReadableException exception) {
    log.error(getClass().getSimpleName(), exception);
    return buildResponse(HttpStatus.BAD_REQUEST, "Request body is invalid!");
  }

  @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleException(
      HttpRequestMethodNotSupportedException exception) {
    log.error(getClass().getSimpleName(), exception);
    return buildResponse(HttpStatus.METHOD_NOT_ALLOWED, exception.getMessage());
  }

  @ExceptionHandler(value = HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleException(
      HttpMediaTypeNotSupportedException exception) {
    log.error(getClass().getSimpleName(), exception);
    return buildResponse(HttpStatus.UNSUPPORTED_MEDIA_TYPE, exception.getMessage());
  }

  @ExceptionHandler(value = Throwable.class)
  public ResponseEntity<ErrorResponse> handleException(Throwable exception) {
    log.error(getClass().getSimpleName(), exception);
    return buildResponse(INTERNAL_SERVER_ERROR, exception.getMessage());
  }

  private List<String> collectErrors(MethodArgumentNotValidException exception) {
    return exception.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
        .collect(Collectors.toList());
  }

  private List<String> collectErrors(BindException exception) {
    return exception.getBindingResult().getFieldErrors().stream()
        .map(fieldError -> fieldError.getField() + " " + fieldError.getDefaultMessage())
        .collect(Collectors.toList());
  }

  private ResponseEntity<ErrorResponse> buildResponse(List<String> errorMessages) {
    String errorMessage = String.join(", ", errorMessages.toArray(new String[0]));
    return buildResponse(HttpStatus.BAD_REQUEST, errorMessage);
  }

  private ResponseEntity<ErrorResponse> buildResponse(HttpStatus httpStatus, String errorMessage) {
    return ResponseEntity.status(httpStatus).body(new ErrorResponse(errorMessage));
  }
}
