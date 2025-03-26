package desafiotinnova.exercicio5.exception;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

public class ValidationException extends RuntimeException {

    private List<FieldError> errors;
    private List<ObjectError> globalErrors;

    // Constructor
    public ValidationException(List<FieldError> errors, List<ObjectError> globalErrors) {
        this.errors = errors;
        this.globalErrors = globalErrors;
    }

    // Combine FieldErrors and ObjectErrors into a Map
    public Map<String, String> getErrors() {
        // Combine both FieldErrors and ObjectErrors into a single map
        Map<String, String> fieldErrorMap = errors.stream()
                .collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));

        // Add global errors (ObjectError) to the map with a key "global"
        globalErrors.forEach(error -> fieldErrorMap.put("global", error.getDefaultMessage()));

        return fieldErrorMap;
    }
}