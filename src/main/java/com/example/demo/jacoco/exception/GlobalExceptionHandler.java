package com.example.demo.jacoco.exception;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    private final static String ERREUR_REST_MESSAGE = "Erreur REST {} : {}";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDTO handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        final BindingResult result = ex.getBindingResult();
        final ErrorDTO errorDTO = handleFieldErrors(result.getFieldErrors());
        LOG.error("Erreur REST de validation {} : {}", HttpStatus.BAD_REQUEST, errorDTO);
        return errorDTO;
    }

    private ErrorDTO handleFieldErrors(List<FieldError> fieldErrors) {
        final ErrorDTO errorDTO = new ErrorDTO(RestErrorConstants.ERR_VALIDATION);
        for (FieldError fieldError : fieldErrors) {
            errorDTO.add(
                    fieldError.getObjectName(),
                    fieldError.getField(),
                    fieldError.getCode(),
                    fieldError.getDefaultMessage(),
                    fieldError.getRejectedValue()
            );
        }
        return errorDTO;
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorDTO handleNotFoundException(ResourceNotFoundException ex) {
        LOG.info(ERREUR_REST_MESSAGE, HttpStatus.NOT_FOUND, ex.getMessage());
        return new ErrorDTO(RestErrorConstants.ERR_DATA_NOT_FOUND, ex.getMessage());
    }

    //    @ExceptionHandler(ResourceNotFoundException.class)
    //    public ResponseEntity resourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    //        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
    //        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    //    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity globleExcpetionHandler(Exception ex, WebRequest request) {
        ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
