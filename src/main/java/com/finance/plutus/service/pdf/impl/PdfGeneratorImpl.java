package com.finance.plutus.service.pdf.impl;

import com.finance.plutus.model.html.Params;
import com.finance.plutus.model.html.Template;
import com.finance.plutus.service.pdf.PdfGenerator;
import com.finance.plutus.util.HtmlUtils;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/** Plutus Created by catalin on 9/7/2020 */
@Service
@RequiredArgsConstructor
public class PdfGeneratorImpl implements PdfGenerator {

  @Override
  public Optional<byte[]> generateSingle(Template template, Params params) {
    try {
      byte[] pdfByteArray = createPdf(template, params);
      return Optional.of(pdfByteArray);
    } catch (DocumentException | IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }

  @Override
  public Optional<byte[]> generateMultiple(Template template, List<Params> paramsList) {
    Map<String, byte[]> pdfMap = new HashMap<>();
    paramsList.forEach(
        params -> {
          try {
            byte[] pdfByteArray = createPdf(template, params);
            pdfMap.put(params.getName(), pdfByteArray);
          } catch (DocumentException | IOException e) {
            e.printStackTrace();
          }
        });
    try {
      return Optional.of(createZip(pdfMap));
    } catch (IOException e) {
      e.printStackTrace();
      return Optional.empty();
    }
  }

  private byte[] createZip(Map<String, byte[]> pdfMap) throws IOException {
    String extension = ".pdf";
    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
    ZipOutputStream zos = new ZipOutputStream(byteArrayOutputStream);
    for (Map.Entry<String, byte[]> pdfEntry : pdfMap.entrySet()) {
      ZipEntry entry = new ZipEntry(pdfEntry.getKey() + extension);
      entry.setSize(pdfEntry.getValue().length);
      zos.putNextEntry(entry);
      zos.write(pdfEntry.getValue());
    }
    zos.closeEntry();
    zos.close();
    return byteArrayOutputStream.toByteArray();
  }

  private byte[] createPdf(Template template, Params params) throws DocumentException, IOException {
    String html = HtmlUtils.parseTemplate(template, params);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(html);
    renderer.layout();
    renderer.createPDF(outputStream);
    byte[] pdfByteArray = outputStream.toByteArray();
    outputStream.close();
    return pdfByteArray;
  }
}
