package com.projeto.teste.ProjetoTestes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projeto.teste.ProjetoTestes.model.Contrato;
import com.projeto.teste.ProjetoTestes.repository.ContratoRepository;

@Service
public class ContratoService {

  @Autowired
  ContratoRepository contratoRepository;

  public Contrato salvar(Contrato contrato){
    return contratoRepository.save(contrato);
  }

  public List<Contrato> listarTodos() {
    return contratoRepository.findAll();
  }
    
}
