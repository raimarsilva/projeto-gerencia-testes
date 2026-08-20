package com.projeto.teste.projetotestes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class CargaHoraria {
  @Id
  int id;
  @Column
  int type;
}