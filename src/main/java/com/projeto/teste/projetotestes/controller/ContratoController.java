package com.projeto.teste.projetotestes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.projeto.teste.projetotestes.model.Contrato;
import com.projeto.teste.projetotestes.model.Unidade;
import com.projeto.teste.projetotestes.service.ContratoService;
import com.projeto.teste.projetotestes.service.SalaryService;

@Controller
@RequestMapping(path = "/")
public class ContratoController {

  @Autowired
  ContratoService contratoService;
  @Autowired
  SalaryService salaryService;

  @GetMapping({ "", "/", "/index" })
  public String index(Model model) {
    model.addAttribute("contrato", new Contrato());
    model.addAttribute("unidades", Unidade.values());
    model.addAttribute("salarios", salaryService.listarTodos());
    return "index";
  }

  @GetMapping({ "/home" })
  public String home(Model model) {
    model.addAttribute("contratos", contratoService.listarTodos());
    return "home";
  }

  @PostMapping(path = "/cadastrar")
  public String salvar(@ModelAttribute Contrato contrato) {
    contratoService.salvar(contrato);
    return "redirect:/";
  }
}
