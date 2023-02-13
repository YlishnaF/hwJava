package org.example.lesson7;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        LocalDate date = LocalDate.of(2023, 2, 3);
        convertAndPrint(new SimpleConverter(), date);
        convertAndPrint(new ISOConverterI(), date);
        convertAndPrint(new NotStandardConverterI(), date);
    }

    private static void convertAndPrint(ConverterI converter, LocalDate date) {
        System.out.println(converter.convert(date));
    }

}
