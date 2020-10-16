package com.finance.plutus.service.pdf;

import com.finance.plutus.model.html.Params;
import com.finance.plutus.model.html.Template;

import java.util.List;
import java.util.Optional;

/** Plutus Created by catalin on 9/7/2020 */
public interface PdfGenerator {
  Optional<byte[]> generateSingle(Template template, Params params);

  Optional<byte[]> generateMultiple(Template template, List<Params> paramsList);
}
