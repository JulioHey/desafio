package desafiotinnova.exercicio5.validation;

import java.util.Optional;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class OptionalIntegerValidator implements ConstraintValidator<ValidAno, Optional<Integer>> {

    @Override
    public boolean isValid(Optional<Integer> value, ConstraintValidatorContext context) {
        if (value.isEmpty()) return true; // Allow null values
        int ano = value.get();
        return ano >= 1886 && ano <= 2025;
    }
}
