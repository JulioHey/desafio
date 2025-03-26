package desafiotinnova.exercicio5.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException() {
        super("Vehicle not found");
    }
    
}
