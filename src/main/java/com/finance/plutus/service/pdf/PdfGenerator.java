package com.finance.plutus.service.pdf;

import java.util.List;
import java.util.Optional;

import com.finance.plutus.model.pdf.Params;
import com.finance.plutus.model.pdf.Template;

/** Plutus Created by catalin on 9/7/2020 */
public interface PdfGenerator {
  Optional<byte[]> generateSingle(Template template, Params params);

  Optional<byte[]> generateMultiple(Template template, List<Params> paramsList);
}
