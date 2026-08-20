package com.projeto.teste.projetotestes.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Titulacao {
  @Id
  int id;

  @Column
  String titulo;
}
