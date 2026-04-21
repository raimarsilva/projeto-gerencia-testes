package com.projeto.teste.ProjetoTestes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Titulacao {
  @Id
  int id;

  @Column
  String titulo;
}
