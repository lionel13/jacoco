package com.example.demo.jacoco.exception;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * DTO pour transferer les messages d'erreur accompagnes d'une liste des champs en erreur.
 */
public class ErrorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Message ou code d'erreur
    private String message;

    // Message d'erreur humainement compréhensible
    private String description;

    private List<FieldErrorDTO> fieldErrors = new ArrayList<>();

    public ErrorDTO() {
        super();
    }

    /**
     * @param message
     *         Message ou code d'erreur
     */
    public ErrorDTO(final String message) {
        this(message, null);
    }

    /**
     * @param message
     *         Message ou code d'erreur
     * @param description
     *         Message d'erreur humainement compréhensible
     */
    public ErrorDTO(final String message, final String description) {
        this.message = message;
        this.description = description;
    }

    /**
     * @param message
     *         Message ou code d'erreur
     * @param description
     *         Message d'erreur humainement compréhensible
     * @param fieldErrors
     *         Liste des erreurs {@link FieldErrorDTO} rencontrées lors de la validation des données.
     */
    public ErrorDTO(final String message, final String description, final List<FieldErrorDTO> fieldErrors) {
        this.message = message;
        this.description = description;
        this.fieldErrors = fieldErrors;
    }

    public void add(final String objectName, final String field, final String code, final String message, final Object rejectedValue) {
        if (fieldErrors == null) {
            fieldErrors = new ArrayList<>();
        }
        fieldErrors.add(new FieldErrorDTO(objectName, field, code, message, rejectedValue));
    }

    public String getMessage() {
        return message;
    }

    public String getDescription() {
        return description;
    }

    public List<FieldErrorDTO> getFieldErrors() {
        return fieldErrors;
    }

    public void setFieldErrors(final List<FieldErrorDTO> fieldErrors) {
        this.fieldErrors = fieldErrors;
    }

    @Override
    public String toString() {
        return ReflectionToStringBuilder.toString(
                this,
                ToStringStyle.NO_CLASS_NAME_STYLE,
                true,
                false
        );
    }

}
