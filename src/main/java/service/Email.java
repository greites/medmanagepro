package service;

import errors.InvalidEmailException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Email {
    
    public static void validate(String email) throws InvalidEmailException {
        String emailPattern = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(emailPattern);
        Matcher matcher = pattern.matcher(email);
        
        if (!matcher.matches()) {
            throw new InvalidEmailException();
        }
    }
}
