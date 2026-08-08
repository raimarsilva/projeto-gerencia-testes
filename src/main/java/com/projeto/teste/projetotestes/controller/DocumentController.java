package com.projeto.teste.projetotestes.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.projeto.teste.projetotestes.service.DocumentService;

@Controller
public class DocumentController {

  final DocumentService documentService;

  DocumentController(DocumentService documentService) {
    this.documentService = documentService;
  }

  @GetMapping("/gerar")
  public ResponseEntity<String> gerarContrato(@RequestParam @NonNull String id) {
    try {
      Long lid = Long.parseLong(id);

      String text = documentService.generateFromDB(lid);

      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.TEXT_HTML);

      return new ResponseEntity<>(text, headers, HttpStatus.OK);
    } catch (Exception e) {
      e.getMessage();
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
  }
}
