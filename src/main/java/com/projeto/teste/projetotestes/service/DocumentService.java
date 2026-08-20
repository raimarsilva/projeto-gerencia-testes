package com.projeto.teste.projetotestes.service;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.projeto.teste.projetotestes.model.Contrato;
import com.projeto.teste.projetotestes.repository.ContratoRepository;

@Service
public class DocumentService {

  private final RestTemplate rest;
  private final TemplateEngine te;
  private final String fileUrl;

  private final ContratoRepository contratoRepository;

  /**
   * Cria um serviço de documentos.
   *
   * @param rest    cliente HTTP usado para baixar o template HTML
   * @param te      mecanismo usado para processar o template
   * @param fileUrl endereço do template HTML
   */
  public DocumentService(RestTemplate rest, @Qualifier("stringTemplateEngine") TemplateEngine te,
      @Value("${GDRIVE_FILE_URL}") String fileUrl, ContratoRepository contratoRepository) {
    this.rest = rest;
    this.te = te;
    this.fileUrl = fileUrl;
    this.contratoRepository = contratoRepository;
  }

  /**
   * Gera um PDF preenchendo o template com os dados do contrato.
   *
   * @param contrato contrato utilizado no preenchimento do template
   * @return conteúdo do PDF gerado, ou um array vazio quando o contrato não
   *         existe
   * @throws IllegalArgumentException quando os dados fornecidos são inválidos
   * @throws IOException              quando ocorre erro durante a geração do PDF
   */
  public String generateFromDB(@NonNull Long lid) throws IllegalArgumentException, IOException {

    validateHtmlTemplateSource();

    Optional<Contrato> contrato = contratoRepository.findById(lid);

    if (contrato.isEmpty())
      return "";

    String rawHtml = htmlTemplateDownload();
    return processTemplate(rawHtml, contrato.get());
  }

  /** Valida se o endereço do template HTML foi configurado. */
  private void validateHtmlTemplateSource() {
    if (fileUrl == null || fileUrl.isBlank()) {
      throw new IllegalStateException("Não encontrou endereço do template.");
    }
  }

  /**
   * Baixa o template HTML configurado.
   *
   * @return conteúdo do template em UTF-8
   */
  private String htmlTemplateDownload() {
    byte[] responseBytes = rest.getForObject(Objects.requireNonNull(fileUrl), byte[].class);

    return new String(responseBytes, StandardCharsets.UTF_8);
  }

  /**
   * Processa o template com os dados do contrato.
   *
   * @param rawHtml template HTML original
   * @param c       contrato usado como variável do template
   * @return HTML processado
   */
  private String processTemplate(String rawHtml, Contrato c) {
    Context context = new Context();

    context.setVariable("contrato", c);

    return te.process(rawHtml, context);
  }
}
