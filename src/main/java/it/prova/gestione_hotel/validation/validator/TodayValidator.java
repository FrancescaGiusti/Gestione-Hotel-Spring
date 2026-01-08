package it.prova.gestione_hotel.validation.validator;

import it.prova.gestione_hotel.validation.annotation.Today;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class TodayValidator implements ConstraintValidator<Today, LocalDate> {

    @Override
    public boolean isValid(LocalDate value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }
        return value.equals(LocalDate.now());
    }
}
