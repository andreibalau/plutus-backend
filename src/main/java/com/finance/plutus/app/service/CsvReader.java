package com.finance.plutus.app.service;

import java.util.List;

/** Plutus Created by Catalin on 11/2/2020 */
public interface CsvReader {
  <T> List<T> loadList(Class<T> type, String file);
}
