package com.example.demo.jacoco.exception;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger LOG = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity resourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request, HandlerMethod handlerMethod) {

        LOG.error("Exception fonctionnelle interceptée par GlobalExceptionHandler : {} {}", ex.getClass().getSimpleName(), ex.toString());

        ErrorDTO errorDetails = new ErrorDTO(new Date(), ex.getReason(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, ex.getStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity globleExcpetionHandler(Exception ex, HttpServletRequest request) {
        ErrorDTO errorDetails = new ErrorDTO(new Date(), ex.getMessage(), request.getRequestURI());
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
