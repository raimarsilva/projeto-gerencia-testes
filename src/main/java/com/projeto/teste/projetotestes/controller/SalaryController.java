package com.projeto.teste.projetotestes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.projeto.teste.projetotestes.model.Salary;
import com.projeto.teste.projetotestes.service.SalaryService;

@Controller
public class SalaryController {

  @Autowired
  SalaryService salaryService;

  public List<Salary> listarSalarios() {
    List<Salary> salarios = salaryService.listarTodos();
    return salarios;
  }
}
