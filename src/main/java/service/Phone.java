package service;

import errors.InvalidPhoneException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Phone {

    public static void validate(String phone) throws InvalidPhoneException {
        String phonePattern = "^\\(?\\d{2}\\)?[ -]?\\d{5}-\\d{4}$";
        Pattern pattern = Pattern.compile(phonePattern);
        Matcher matcher = pattern.matcher(phone);
        
        if (!matcher.matches()) {
            throw new InvalidPhoneException();
        }
    }
}