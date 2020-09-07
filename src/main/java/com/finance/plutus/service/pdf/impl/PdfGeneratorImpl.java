package com.finance.plutus.service.pdf.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import com.finance.plutus.model.pdf.Params;
import com.finance.plutus.model.pdf.Template;
import com.finance.plutus.service.pdf.PdfGenerator;
import com.lowagie.text.DocumentException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

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
    ByteArrayOutputStream baos = new ByteArrayOutputStream();
    ZipOutputStream zos = new ZipOutputStream(baos);
    for (Map.Entry<String, byte[]> reporte : pdfMap.entrySet()) {
      ZipEntry entry = new ZipEntry(reporte.getKey() + extension);
      entry.setSize(reporte.getValue().length);
      zos.putNextEntry(entry);
      zos.write(reporte.getValue());
    }
    zos.closeEntry();
    zos.close();
    return baos.toByteArray();
  }

  private byte[] createPdf(Template template, Params params) throws DocumentException, IOException {
    String html = parseTemplate(template, params);
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    ITextRenderer renderer = new ITextRenderer();
    renderer.setDocumentFromString(html);
    renderer.layout();
    renderer.createPDF(outputStream);
    byte[] pdfByteArray = outputStream.toByteArray();
    outputStream.close();
    return pdfByteArray;
  }

  private String parseTemplate(Template template, Params params) {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("/templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    Context context = new Context();
    context.setVariables(params.getMap());
    return templateEngine.process(template.getName(), context);
  }
}
