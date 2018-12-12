package com.example.demo.jacoco.style;

import com.example.demo.jacoco.exception.ResourceNotFoundException;

public class StyleNotFoundException extends ResourceNotFoundException {

    public StyleNotFoundException(long personId) {
        super("Style non trouvée pour l'id : " + personId);
    }
}
