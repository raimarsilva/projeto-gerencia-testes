package com.projeto.teste.ProjetoTestes.repository;

import com.projeto.teste.ProjetoTestes.model.Advogado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdvogadoRepository extends JpaRepository<Advogado, Long> {
}
