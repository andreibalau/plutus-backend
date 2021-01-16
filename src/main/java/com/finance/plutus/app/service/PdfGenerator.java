package com.finance.plutus.app.service;

import java.util.List;
import java.util.Optional;

/** Plutus Created by catalin on 9/7/2020 */
public interface PdfGenerator {
  Optional<byte[]> generateSingle(Template template, Params<?> params);

  Optional<byte[]> generateMultiple(Template template, List<Params<?>> paramsList);
}
