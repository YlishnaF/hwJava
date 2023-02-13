package org.example.lesson7;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Converter {

    public static String convert(LocalDate date) {
        // 01.01.2023
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

}
