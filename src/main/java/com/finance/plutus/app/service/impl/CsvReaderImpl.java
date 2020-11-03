package com.finance.plutus.app.service.impl;

import com.fasterxml.jackson.databind.MappingIterator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.finance.plutus.app.service.CsvReader;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.Collections;
import java.util.List;

/** Plutus Created by Catalin on 11/2/2020 */
@Service
@RequiredArgsConstructor
public class CsvReaderImpl implements CsvReader {

  @Override
  public <T> List<T> loadList(Class<T> type, String file) {
    try {
      CsvSchema bootstrapSchema = CsvSchema.emptySchema().withHeader();
      CsvMapper mapper = new CsvMapper();
      byte[] data = decodeFile(file);
      MappingIterator<T> readValues = mapper.readerFor(type).with(bootstrapSchema).readValues(data);
      return readValues.readAll();
    } catch (Exception e) {
      return Collections.emptyList();
    }
  }

  private byte[] decodeFile(String file) {
    return Base64.getDecoder().decode(file);
  }
}
