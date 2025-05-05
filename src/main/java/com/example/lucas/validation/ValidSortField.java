package com.example.lucas.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = SortFieldValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidSortField {
    String message() default "Invalid sort field";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    // Optionally, you can define allowed fields directly
    String[] allowed() default {};
}