package com.projeto.teste.projetotestes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.projeto.teste.projetotestes.model.Salary;

public interface SalaryRepository extends JpaRepository<Salary, Integer> {
}
