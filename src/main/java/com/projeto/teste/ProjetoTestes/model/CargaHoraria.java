package com.projeto.teste.ProjetoTestes.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CargaHoraria {
  @Id
  int id;
  @Column
  int type;
}