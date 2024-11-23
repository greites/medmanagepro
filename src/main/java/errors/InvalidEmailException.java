package errors;

public class InvalidEmailException extends RuntimeException {
    public InvalidEmailException() {
        super("The email address is invalid.");
    }
}
