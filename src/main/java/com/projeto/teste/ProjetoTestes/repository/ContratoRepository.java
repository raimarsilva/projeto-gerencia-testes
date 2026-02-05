package com.projeto.teste.ProjetoTestes.repository;

import org.springframework.stereotype.Repository;

import com.projeto.teste.ProjetoTestes.model.Contrato;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long>{
    
}
