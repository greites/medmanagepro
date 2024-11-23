package errors;

public class InvalidDateException extends RuntimeException {
    public InvalidDateException() {
        super("Invalid date."); 
    }
}
