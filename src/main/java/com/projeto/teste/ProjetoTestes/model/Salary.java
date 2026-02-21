package com.projeto.teste.ProjetoTestes.model;

public enum Salary {
  T20G(3000), T20E(3200), T20M(3400), T20D(3800),
  T40G(5000), T40E(5200), T40M(5400), T40D(5800),
  VJ(10000), VA(12000), VT(14000);

  private final double value;

  Salary(double value) {
    this.value = value;
  }

  public double getValue() {
    return value;
  }
}
