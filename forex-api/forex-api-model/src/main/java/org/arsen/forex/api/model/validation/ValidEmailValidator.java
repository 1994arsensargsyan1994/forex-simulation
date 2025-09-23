package org.arsen.forex.api.model.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.regex.Pattern;

public class ValidEmailValidator implements ConstraintValidator<ValidEmail, String> {

    private boolean required;
    private int minTld;

    private static final Pattern EMAIL = Pattern.compile(
            "^[A-Za-z0-9.!#$%&'*+/=?^_`{|}~-]+" +            // local
                    "@" +
                    "(?:[A-Za-z0-9](?:[A-Za-z0-9-]{0,61}[A-Za-z0-9])\\.)+" + // domain labels
                    "[A-Za-z]{2,63}$"                                // TLD
    );

    @Override
    public void initialize(ValidEmail anno) {
        this.required = anno.required();
        this.minTld = Math.max(1, anno.minTld());
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext ctx) {
        if (value == null || value.isBlank()) {
            return !required; // valid if not required
        }

        if (!EMAIL.matcher(value).matches()) return false;

        int lastDot = value.lastIndexOf('.');
        if (lastDot <= value.lastIndexOf('@') || lastDot == value.length() - 1) return false;
        int tldLen = value.length() - lastDot - 1;
        return tldLen >= minTld;
    }
}