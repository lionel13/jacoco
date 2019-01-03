package com.example.demo.jacoco.utils.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class EnumValueValidator  implements ConstraintValidator<Enum, String>
{
    private Enum annotation;

    @Override
    public void initialize(Enum annotation)
    {
        this.annotation = annotation;
    }

    @Override
    public boolean isValid(String valueForValidation, ConstraintValidatorContext constraintValidatorContext)
    {

        return Arrays.asList(this.annotation.enumClass().getFields()).stream()
                .anyMatch(f -> valueForValidation.equals(f.getName()));

    }
}