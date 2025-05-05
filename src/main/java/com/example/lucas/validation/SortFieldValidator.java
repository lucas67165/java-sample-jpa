package com.example.lucas.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

public class SortFieldValidator implements ConstraintValidator<ValidSortField, String> {
    private String[] allowedFields;

    @Override
    public void initialize(ValidSortField constraintAnnotation) {
        allowedFields = constraintAnnotation.allowed();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Allow null values (if desired, you can enforce non-null separately)
        if (value == null) {
            return true;
        }
        return Arrays.asList(allowedFields).contains(value);
    }
}
