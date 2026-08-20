package com.projeto.teste.projetotestes.controller;

import jakarta.validation.Valid;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import com.projeto.teste.projetotestes.model.Contrato;
import com.projeto.teste.projetotestes.model.Unidade;
import com.projeto.teste.projetotestes.service.ContratoService;
import com.projeto.teste.projetotestes.service.SalaryService;

@Controller
@RequestMapping(path = "/")
public class ContratoController {

  final ContratoService contratoService;
  final SalaryService salaryService;

  ContratoController(ContratoService contratoService, SalaryService salaryService) {
    this.contratoService = contratoService;
    this.salaryService = salaryService;
  }

  @GetMapping({ "", "/", "/cadastro" })
  public String cadastro(Model model) {
    model.addAttribute("contrato", new Contrato());
    model.addAttribute("unidades", Unidade.values());
    model.addAttribute("salarios", salaryService.listarTodos());
    return "cadastro";
  }

  @GetMapping({ "/home" })
  public String home(Model model) {
    model.addAttribute("contratos", contratoService.listarTodos());
    return "home";
  }

  @PostMapping(path = "/cadastrar")
  public String salvar(@Valid @ModelAttribute @NonNull Contrato contrato, BindingResult result, Model model) {
    System.out.println(">>> Tentando salvar contrato: " + contrato.getNumProc());

    if (result.hasErrors()) {
      System.out.println(">>> O formulário possui os seguintes erros de validação:");
      result.getAllErrors().forEach(erro -> System.out.println(" - " + erro.toString()));

      model.addAttribute("unidades", Unidade.values());
      model.addAttribute("salarios", salaryService.listarTodos());
      return "cadastro";
    }

    contratoService.salvar(contrato);
    System.out.println(">>> Contrato salvo com sucesso!");
    return "redirect:/home";
  }

}
