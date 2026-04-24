package com.projeto.teste.ProjetoTestes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.teste.ProjetoTestes.model.Salary;
import com.projeto.teste.ProjetoTestes.repository.SalaryRepository;

@Service
public class SalaryService {

  @Autowired
  SalaryRepository salaryRepository;

  public List<Salary> listarTodos() {
    return salaryRepository.findAll();
  }

}
