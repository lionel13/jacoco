package com.example.demo.jacoco.exception;

import static org.springframework.http.HttpStatus.NOT_FOUND;

import org.springframework.web.server.ResponseStatusException;

public class ResourceNotFoundException extends ResponseStatusException {

    /**
     * Quelle classe remonte l'exception.
     */
    private final Class entite;

    /**
     * Quelle classe remonte l'exception.
     */
    private final long id;

    /**
     * Quelle classe remonte l'exception.
     */
    private final String className;

    /**
     * Quelle méthode remonte l'exception.
     */
    private final String methodName;

    public ResourceNotFoundException(Class entite, long entiteId) {

        super(NOT_FOUND, entite.getSimpleName() + " non trouvé pour l'id " + entiteId);

        this.className = this.getStackTrace()[0].getClassName();
        this.methodName = this.getStackTrace()[0].getMethodName();
        this.id = entiteId;
        this.entite = entite;

    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {

        StringBuilder result = new StringBuilder();
        result.append(this.getClass().getSimpleName());
        result.append(" { className : ");
        result.append(this.className);
        result.append(", methodName : ");
        result.append(this.methodName);
        result.append(", entite : ");
        result.append(this.entite.getSimpleName());
        result.append(", id : ");
        result.append(this.id);
        result.append(", message : ");
        result.append(this.getReason());
        result.append(", cause : ");
        result.append(this.getCause());
        result.append("}");

        return result.toString();
    }
}
