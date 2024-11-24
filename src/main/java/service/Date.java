package service;

import errors.InvalidDateException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public final class Date {
    
    public static LocalDate format(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(date, formatter);
        } catch (DateTimeException e) {
            throw new InvalidDateException();
        }   
    } 
}
