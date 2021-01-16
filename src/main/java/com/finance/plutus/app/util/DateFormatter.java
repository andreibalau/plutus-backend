package com.finance.plutus.app.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/** Plutus Created by Catalin on 1/16/2021 */
public class DateFormatter {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

  public static String formatDate(LocalDate localDate) {
    return localDate.format(formatter);
  }
}
