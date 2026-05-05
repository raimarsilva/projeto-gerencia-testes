package com.projeto.teste.ProjetoTestes.service;

import java.io.ByteArrayOutputStream;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import com.projeto.teste.ProjetoTestes.model.Contrato;

@Service
public class DocumentService {

  @Autowired
  private RestTemplate rest;

  @Autowired
  @Qualifier("stringTemplateEngine")
  private TemplateEngine te;

  @Value("${SUPABASE_URL}")
  private String dburl;

  public byte[] generateFromDB(Optional<Contrato> contrato) throws Exception {

    String docurl = dburl
        + "/storage/v1/object/sign/documents/contrato.html?token=eyJraWQiOiJzdG9yYWdlLXVybC1zaWduaW5nLWtleV9mYjVhNjUxMi1iMGVlLTQyOGQtYTU0OS0yODY5ZGM5NWViMjEiLCJhbGciOiJIUzI1NiJ9.eyJ1cmwiOiJkb2N1bWVudHMvY29udHJhdG8uaHRtbCIsImlhdCI6MTc3NzkyNDA0OSwiZXhwIjoxODA5NDYwMDQ5fQ.GRiHD-njRZVNt20UrtlHgoEZixKlh7Qy1Q7UaqlUpW4";

    // faz um GET nesse endereço a fim de recuperar o documento armazanado no banco.
    String completeurl = rest.getForObject(docurl, String.class);

    // cria um contexto thymeleaf.
    Context context = new Context();
    context.setVariable("contrato", contrato);

    String htmlProcessed = te.process(completeurl, context);

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    PdfRendererBuilder builder = new PdfRendererBuilder();

    builder.withHtmlContent(htmlProcessed, null);
    builder.toStream(out);
    builder.run();

    return out.toByteArray();
  }
}
