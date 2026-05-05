package com.projeto.teste.ProjetoTestes.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
  public String gerarContrato(@RequestParam @NonNull Long param) {
    Optional<Contrato> contrato = contratoRepository.findById(param);
    try {
      return documentService.generateFromDB(contrato).toString();
    } catch (Exception e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return "Contrato não encontrado.";
  }
}
