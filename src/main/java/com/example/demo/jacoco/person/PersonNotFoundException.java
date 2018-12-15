package com.example.demo.jacoco.person;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.example.demo.jacoco.exception.ResourceNotFoundException;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends ResourceNotFoundException {

    public PersonNotFoundException(long personId) {
        super(Person.class, personId);

    }

}
