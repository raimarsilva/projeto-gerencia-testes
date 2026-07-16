package com.projeto.teste.projetotestes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.projeto.teste.projetotestes.model.Contrato;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
  @Override
  @NonNull
  default <S extends Contrato> S save(@NonNull S entity) {
    throw new UnsupportedOperationException("Unimplemented method 'save'");
  }
}
