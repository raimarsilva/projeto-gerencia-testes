package com.projeto.teste.projetotestes.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.ZoneId;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "salary")
@AllArgsConstructor
@Builder
public class Salary {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private int id;

  @Column(nullable = false)
  @Builder.Default
  private boolean active = true;

  @Column(name = "created_date", nullable = false, updatable = false)
  @DateTimeFormat(iso = ISO.DATE)
  private LocalDate createdDate;

  @Column(nullable = false, precision = 10, scale = 2)
  private BigDecimal value;

  @PrePersist
  protected void onCreate() {
    if (this.createdDate == null)
      createdDate = LocalDate.now(ZoneId.of("America/Recife"));
  }
}
