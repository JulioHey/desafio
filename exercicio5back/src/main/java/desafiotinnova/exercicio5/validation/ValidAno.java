package desafiotinnova.exercicio5.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = OptionalIntegerValidator.class)
@Target({ ElementType.FIELD, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAno {
    String message() default "O ano deve estar entre 1886 e 2025";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
