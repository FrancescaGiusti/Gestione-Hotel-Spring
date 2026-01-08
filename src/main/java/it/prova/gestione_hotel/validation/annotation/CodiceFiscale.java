package it.prova.gestione_hotel.validation.annotation;

import it.prova.gestione_hotel.validation.validator.CodiceFiscaleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

    @Constraint(validatedBy = CodiceFiscaleValidator.class)
    @Target({ ElementType.FIELD })
    @Retention(RetentionPolicy.RUNTIME)
    public @interface CodiceFiscale {

        String message() default "Codice fiscale non valido";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
    }
