package com.projeto.teste.projetotestes.controller;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.projeto.teste.projetotestes.model.Salary;
import com.projeto.teste.projetotestes.service.SalaryService;

@Controller
public class SalaryController {

  final SalaryService salaryService;

  SalaryController(SalaryService salaryService) {
    this.salaryService = salaryService;
  }

  public List<Salary> listarSalarios() {
    return salaryService.listarTodos();
  }
}
