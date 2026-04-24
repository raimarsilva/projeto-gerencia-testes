package com.projeto.teste.ProjetoTestes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projeto.teste.ProjetoTestes.model.Salary;

@Repository
public interface SalaryRepository extends JpaRepository<Salary, Integer> {
  /*
   * @Transactional
   * 
   * @Modifying
   * 
   * @Query(value = "DELETE FROM tb_vinculacao WHERE id_processo = ?1",
   * nativeQuery = true) public void deletarProcessoVinculado(Long id);
   */
}
