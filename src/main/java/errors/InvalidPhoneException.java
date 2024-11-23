package errors;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException() {
        super("The phone number is invalid.");
    }
}
