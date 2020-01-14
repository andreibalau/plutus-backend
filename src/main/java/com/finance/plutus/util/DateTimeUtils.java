package com.finance.plutus.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Plutus
 * Created by catalin on 30.09.2019
 */
public final class DateTimeUtils {

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    private DateTimeUtils() {
        throw new IllegalStateException("You cannot instantiate this class");
    }

    public static LocalDate parse(String date, String pattern) {
        return LocalDate.parse(date, DateTimeFormatter.ofPattern(pattern));
    }

}
