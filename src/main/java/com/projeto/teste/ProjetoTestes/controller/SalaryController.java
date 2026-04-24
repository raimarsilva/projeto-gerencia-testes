package com.projeto.teste.ProjetoTestes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.projeto.teste.ProjetoTestes.model.Salary;
import com.projeto.teste.ProjetoTestes.service.SalaryService;

@Controller
public class SalaryController {

  @Autowired
  SalaryService salaryService;

  public List<Salary> listarSalarios() {
    List<Salary> salarios = salaryService.listarTodos();
    return salarios;
  }
}
