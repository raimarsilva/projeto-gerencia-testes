package com.projeto.teste.projetotestes.controller;

import java.util.Optional;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.teste.projetotestes.model.Contrato;
import com.projeto.teste.projetotestes.repository.ContratoRepository;
import com.projeto.teste.projetotestes.service.DocumentService;

@Controller
public class DocumentController {

  final DocumentService documentService;

  final ContratoRepository contratoRepository;

  DocumentController(DocumentService documentService, ContratoRepository contratoRepository) {
    this.documentService = documentService;
    this.contratoRepository = contratoRepository;
  }

  @GetMapping("/gerar")
  public ResponseEntity<byte[]> gerarContrato(@RequestParam @NonNull String id) {
    try {
      Long lid = Long.parseLong(id);

      Optional<Contrato> contrato = contratoRepository.findById(lid);

      if (contrato.isEmpty()) {
        return ResponseEntity.notFound().build();
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
