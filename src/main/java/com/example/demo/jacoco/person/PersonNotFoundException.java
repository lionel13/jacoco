package com.example.demo.jacoco.person;

import com.example.demo.jacoco.exception.ResourceNotFoundException;

public class PersonNotFoundException extends ResourceNotFoundException {

    public PersonNotFoundException(long personId) {
        super("Personne non trouvée pour l'id : " + personId);
    }
}
