package com.projeto.teste.projetotestes.service;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
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

  @Autowired
  private RestTemplate rest;

  @Autowired
  @Qualifier("stringTemplateEngine")
  private TemplateEngine te;

  @Value("${GDRIVE_FILE_URL}")
  private String fileurl;

  public byte[] generateFromDB(Optional<Contrato> contrato) throws Exception {
    if (contrato.isEmpty())
      return new byte[0];

    if (fileurl == null)
      throw new Exception("Endereço do arquivo inválido.");

    byte[] responseBytes = rest.getForObject(fileurl, byte[].class);

    String htmlpuro = new String(responseBytes, StandardCharsets.UTF_8);

    Context context = new Context();

    context.setVariable("contrato", contrato.get());

    String htmlProcessed = te.process(htmlpuro, context);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PdfRendererBuilder builder = new PdfRendererBuilder();

    builder.withHtmlContent(htmlProcessed, null);
    builder.toStream(out);
    builder.run();

    return out.toByteArray();
  }
}
