package com.example.demo.jacoco.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

public class DateValidator implements ConstraintValidator<Date, String> {

    private Date annotation;

    @Override
    public void initialize(Date annotation) {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String dateForValidation, ConstraintValidatorContext constraintValidatorContext) {
        DateTimeFormatter formatter = new DateTimeFormatterBuilder().appendPattern(this.annotation.pattern())
                .parseStrict().toFormatter();

        try {
            LocalDate.parse(dateForValidation, formatter);
            return true;
        } catch (DateTimeParseException e) {
        }

        return false;
    }
}
