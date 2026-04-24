package com.projeto.teste.ProjetoTestes.controller;

import com.projeto.teste.ProjetoTestes.model.Contrato;
import com.projeto.teste.ProjetoTestes.model.Unidade;
import com.projeto.teste.ProjetoTestes.service.ContratoService;
import com.projeto.teste.ProjetoTestes.service.SalaryService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    model.addAttribute("contratos", contratoService.listarTodos());
    model.addAttribute("unidades", Unidade.values());
    model.addAttribute("salarios", salaryService.listarTodos());
    return "index";
  }

  // Rota para salvar
  @PostMapping(path = "/cadastrar")
  public String salvar(@ModelAttribute Contrato contrato) {
    contratoService.salvar(contrato);
    return "redirect:/";
  }

  // Rota para listar todos
  @GetMapping(path = "/list")
  public List<Contrato> listarTodos() {
    List<Contrato> contratos = contratoService.listarTodos();
    return contratos;
  }
}
