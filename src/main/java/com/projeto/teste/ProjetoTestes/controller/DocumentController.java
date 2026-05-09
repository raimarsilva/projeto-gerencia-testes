package com.projeto.teste.ProjetoTestes.controller;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import com.projeto.teste.ProjetoTestes.model.Contrato;
import com.projeto.teste.ProjetoTestes.repository.ContratoRepository;
import com.projeto.teste.ProjetoTestes.service.DocumentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DocumentController {

  @Autowired
  DocumentService documentService;

  @Autowired
  ContratoRepository contratoRepository;

  @GetMapping("/gerar")
  public ResponseEntity<byte[]> gerarContrato(@RequestParam @NonNull String id) {
    try {
      Long lid = Long.parseLong(id);

      Optional<Contrato> contrato = contratoRepository.findById(lid);

      if (contrato.isEmpty()) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
      }

      byte[] pdfBytes = documentService.generateFromDB(contrato);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.APPLICATION_PDF);

      headers.setContentDisposition(ContentDisposition.inline().filename("Contrato " + lid + ".pdf").build());

      return new ResponseEntity<>(pdfBytes, headers, HttpStatus.OK);
    } catch (Exception e) {
      e.getMessage();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
