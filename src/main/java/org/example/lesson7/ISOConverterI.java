package org.example.lesson7;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ISOConverterI implements ConverterI {
    @Override
    public String convert(LocalDate date) {
        return date.format(DateTimeFormatter.ISO_DATE);
    }
}
