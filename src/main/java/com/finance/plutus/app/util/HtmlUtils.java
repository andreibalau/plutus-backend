package com.finance.plutus.app.util;

import com.finance.plutus.invoice.model.html.Params;
import com.finance.plutus.invoice.model.html.Template;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

/** Plutus Created by Catalin on 10/16/2020 */
public class HtmlUtils {

  public static String parseTemplate(Template template, Params params) {
    ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
    templateResolver.setPrefix("/templates/");
    templateResolver.setSuffix(".html");
    templateResolver.setTemplateMode(TemplateMode.HTML);
    TemplateEngine templateEngine = new TemplateEngine();
    templateEngine.setTemplateResolver(templateResolver);
    Context context = new Context();
    context.setVariables(params.getMap());
    return templateEngine.process(template.getValue(), context);
  }
}
