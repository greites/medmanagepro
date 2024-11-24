package service;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Time {
    public static LocalTime format(String time) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        try {
            return LocalTime.parse(time, formatter);
        } catch (DateTimeException e) {
            //throw new InvalidDateException();
            return null;
        }   
    } 
}
