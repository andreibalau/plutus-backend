package com.finance.plutus.service.pdf;

import com.finance.plutus.model.html.Params;
import com.finance.plutus.model.html.Template;

import java.util.List;
import java.util.Optional;

/** Plutus Created by catalin on 9/7/2020 */
public interface PdfGenerator {
  @Deprecated
  Optional<byte[]> generateSingle(Template template, Params params);

  @Deprecated
  Optional<byte[]> generateMultiple(Template template, List<Params> paramsList);
}
