package it.prova.gestione_hotel.validation.annotation;

import it.prova.gestione_hotel.validation.validator.TodayValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = TodayValidator.class)
@Target({ ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface Today {

    String message() default "La data deve essere quella odierna";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
