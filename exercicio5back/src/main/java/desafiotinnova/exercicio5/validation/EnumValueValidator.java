package desafiotinnova.exercicio5.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Optional;

public class EnumValueValidator implements ConstraintValidator<EnumValue, Object> {

    private String[] enumConstants;

    @Override
    public void initialize(EnumValue annotation) {
        // Get all constants of the enum class and convert them to String[]
        this.enumConstants = extractEnumNames(annotation.enumClass());
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return true; // Let @NotNull handle this
        }

        String strValue;

        // Check if the value is Optional<String>
        if (value instanceof Optional<?> optionalValue) {
            if (optionalValue.isEmpty()) {
                return true; // An empty Optional is considered valid
            }
            if (optionalValue.get() instanceof String stringValue) {
                strValue = stringValue;
            } else {
                return false; // Optional contains an invalid type
            }
        } else if (value instanceof String) {
            strValue = (String) value;
        } else {
            return false; // Unsupported type
        }

        // Check if the value matches one of the enum constants
        for (String enumConstant : enumConstants) {
            if (enumConstant.equals(strValue)) {
                return true;
            }
        }

        return false; // Not a valid enum constant
    }

    private String[] extractEnumNames(Class<? extends Enum<?>> enumClass) {
        Enum<?>[] enumValues = enumClass.getEnumConstants();
        String[] enumNames = new String[enumValues.length];

        for (int i = 0; i < enumValues.length; i++) {
            enumNames[i] = enumValues[i].name(); // Extract the enum name as a string
        }

        return enumNames;
    }
}
