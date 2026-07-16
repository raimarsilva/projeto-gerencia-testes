package com.projeto.teste.projetotestes.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.projeto.teste.projetotestes.model.Salary;
import com.projeto.teste.projetotestes.repository.SalaryRepository;

@Service
public class SalaryService {

  @Autowired
  SalaryRepository salaryRepository;

  public List<Salary> listarTodos() {
    return salaryRepository.findAll(Sort.by(Sort.Direction.ASC, "id"));
  }

}
