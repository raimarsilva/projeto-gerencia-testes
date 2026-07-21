package com.projeto.teste.projetotestes.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.projeto.teste.projetotestes.model.Contrato;

@Service
public class DocumentService {

  private final RestTemplate rest;
  private final TemplateEngine te;
  private final String fileUrl;

  public DocumentService(RestTemplate rest, @Qualifier("stringTemplateEngine") TemplateEngine te,
      @Value("${GDRIVE_FILE_URL}") String fileUrl) {
    this.rest = rest;
    this.te = te;
    this.fileUrl = fileUrl;
  }

  public byte[] generateFromDB(Optional<Contrato> contrato) throws IllegalArgumentException, IOException {
    if (contrato.isEmpty())
      return new byte[0];

    validateHtmlTemplateSource();

    String rawHtml = htmlTemplateDownload();
    String processedHtml = processTemplate(rawHtml, contrato.get());

    return renderPdfFromHtml(processedHtml);
  }

  private void validateHtmlTemplateSource() {
    if (fileUrl == null || fileUrl.isBlank()) {
      throw new IllegalStateException("Não encontrou endereço do template.");
    }
  }

  private String htmlTemplateDownload() {
    byte[] responseBytes = rest.getForObject(fileUrl, byte[].class);
    if (responseBytes == null || responseBytes.length == 0) {
      throw new IllegalStateException("Falha ao baixar o modelo do documento");
    }

    return new String(responseBytes, StandardCharsets.UTF_8);
  }

  private String processTemplate(String rawHtml, Contrato c) {
    Context context = new Context();

    context.setVariable("contrato", c);

    return te.process(rawHtml, context);
  }

  private byte[] renderPdfFromHtml(String htmlContent) throws IOException {
    try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
      PdfRendererBuilder builder = new PdfRendererBuilder();
      builder.withHtmlContent(htmlContent, null);
      builder.toStream(out);
      builder.run();
      return out.toByteArray();
    }
  }
}
