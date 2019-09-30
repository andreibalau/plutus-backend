package com.finance.plutus.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.ResponseEntity.status;

@ControllerAdvice
public class ExceptionHandlerConfig {

    private static final String EMPTY_SPACE = " ";

    @ExceptionHandler(value = Throwable.class)
    public ResponseEntity<Object> handleException(Throwable exception) {
        if (exception instanceof ServiceException) {
            ServiceException serviceException = (ServiceException) exception;
            return getResponse(serviceException).body(ErrorDto.from(serviceException));
        }
        return new ResponseEntity<>(ErrorDto.from(ServiceException.generalException(exception.getMessage())), INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        List<String> errorMessages = exception
                .getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fieldError -> fieldError.getField() + EMPTY_SPACE + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(ErrorDto.from(ServiceException.invalidJson(errorMessages)), BAD_REQUEST);
    }

    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity<?> handleNoBodyException(HttpMessageNotReadableException exception) {
        return new ResponseEntity<>(ErrorDto.from(ServiceException.noBody()), BAD_REQUEST);
    }

    @ExceptionHandler(value = TransactionSystemException.class)
    public ResponseEntity<?> handleTransactionSystemException(TransactionSystemException exception) {
        Throwable t = (exception).getOriginalException().getCause();

        if (t instanceof ConstraintViolationException) {
            List<String> errorMessages = ((ConstraintViolationException) t).getConstraintViolations()
                    .stream()
                    .map(constraintViolation -> constraintViolation
                            .getPropertyPath().toString() + EMPTY_SPACE +
                            constraintViolation.getMessage())
                    .collect(Collectors.toList());
            return new ResponseEntity<>(ErrorDto.from(ServiceException.constraintsViolation(errorMessages)), BAD_REQUEST);
        }
        return new ResponseEntity<>(ErrorDto.from(ServiceException.generalException(exception.getMessage())), INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity.BodyBuilder getResponse(ServiceException serviceException) {
        switch(serviceException.getCode()) {
            case ServiceException.NOT_FOUND_ERROR_CODE:
                return status(NOT_FOUND);
            default:
                return status(BAD_REQUEST);
        }
    }

}
