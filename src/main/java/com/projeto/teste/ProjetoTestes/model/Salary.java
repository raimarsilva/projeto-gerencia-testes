package com.projeto.teste.ProjetoTestes.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "salary")
public class Salary {

  @Id
  private int id;

  @Column
  private boolean active;

  @Column
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate createdDate;

  @Column
  private double value;

  Salary(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}
