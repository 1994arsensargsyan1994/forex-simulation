package org.arsen.forex.api.model.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = ValidEmailValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidEmail {

    String message() default "invalid email format";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /** If false, null/blank are considered valid (combine with @NotBlank to require). */
    boolean required() default false;

    /** Minimum top-level domain length (e.g., 2 -> .com ok, .c not ok). */
    int minTld() default 2;
}

