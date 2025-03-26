package desafiotinnova.exercicio5.response;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import desafiotinnova.exercicio5.exception.NotFoundException;
import desafiotinnova.exercicio5.exception.ValidationException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // Handle other generic exceptions
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(ValidationException ex) {
        return new ResponseEntity<>(ex.getErrors(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(NotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleGeneralException(Exception ex) {
        Map<String, String> errorResponse = new HashMap<>();
        System.out.println("CARALHO" + ex.getMessage());
        errorResponse.put("Error:", "Unxpected error occurred");
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
