package com.benwillcabinets.benwillestimator.service;

import com.benwillcabinets.benwillestimator.domain.ProjectEstimate;
import com.lowagie.text.DocumentException;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service("pdfGenerator")
public class PdfGenerator {

    public byte[] createRefacingItemsPdf(ProjectEstimate project) throws Exception {
        String html = parseThymeleafTemplate("pdf-templates/refacing-template", project);
        return generatePdfFromHtml(html);
    }

    public byte[] createSchedulingItemsPdf(ProjectEstimate project) throws Exception {
        String html = parseThymeleafTemplate("pdf-templates/scheduling-template", project);
        return generatePdfFromHtml(html);
    }

    public byte[] createQuoteItemsPdf(ProjectEstimate project) throws Exception {
        String html = parseThymeleafTemplate("pdf-templates/quote-template", project);
        return generatePdfFromHtml(html);
    }

    private String parseThymeleafTemplate(String templateName, ProjectEstimate project) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode(TemplateMode.HTML);

        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.setTemplateResolver(templateResolver);

        Context context = new Context();
        context.setVariable("project", project);

        return templateEngine.process(templateName, context);
    }


    private byte[] generatePdfFromHtml(String html) throws DocumentException, IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try {
            ITextRenderer renderer = new ITextRenderer();
            renderer.setDocumentFromString(html);
            renderer.layout();
            renderer.createPDF(outputStream);
            return outputStream.toByteArray();
        } finally {
            outputStream.close();
        }
    }
}
