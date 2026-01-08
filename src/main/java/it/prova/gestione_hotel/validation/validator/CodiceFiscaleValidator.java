package it.prova.gestione_hotel.validation.validator;

import it.prova.gestione_hotel.validation.annotation.CodiceFiscale;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CodiceFiscaleValidator
        implements ConstraintValidator<CodiceFiscale, String> {

    private static final String CF_REGEX =
            "^[A-Z]{6}[0-9]{2}[A-EHLMPRST]" +
                    "[0-9]{2}[A-Z][0-9]{3}[A-Z]$";

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (value == null || value.isBlank()) {
            return true;
        }

        return value.matches(CF_REGEX);
    }
}
