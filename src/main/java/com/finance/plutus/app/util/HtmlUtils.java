package com.finance.plutus.app.util;

import com.finance.plutus.app.service.Template;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.util.Map;

/** Plutus Created by Catalin on 10/16/2020 */
public class HtmlUtils {

  public static String parseTemplate(Template template, Map<String, Object> params) {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("/templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    Context context = new Context();
    context.setVariables(params);
    return templateEngine.process(template.getValue(), context);
  }
}
