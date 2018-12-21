package com.example.demo.jacoco.exception;

import java.io.Serializable;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * Represente un champ en erreur.
 */
public class FieldErrorDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Objet validé contenant le champ en erreur
    private String objectName;

    // Nom du champ ou de la donnée en erreur
    private String field;

    //  code d'erreur
    private String code;

    // Message d'erreur
    private String message;

    // Message d'erreur
    private Object value;

    public FieldErrorDTO() {
        super();
    }

    /**
     * @param dto
     *         Objet validé contenant le champ en erreur
     * @param field
     *         Nom du champ ou de la donnée en erreur
     * @param message
     *         Message ou code d'erreur
     */
    public FieldErrorDTO(String dto, String field, String code, String message, Object rejectedValue) {
        this.objectName = dto;
        this.field = field;
        this.code = code;
        this.message = message;
        this.value = rejectedValue;
    }

    public String getObjectName() {
        return objectName;
    }

    public String getField() {
        return field;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public Object getValue() {
        return value;
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
