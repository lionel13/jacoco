package com.example.demo.jacoco.exception;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.springframework.web.server.ResponseStatusException;

public class ParametreRestInvalideException extends ResponseStatusException {

    public ParametreRestInvalideException(String reason, Throwable cause) {
        super(BAD_REQUEST, reason, cause);
    }

}
