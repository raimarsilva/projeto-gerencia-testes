package com.projeto.teste.ProjetoTestes.repository;

import com.projeto.teste.ProjetoTestes.model.Advogado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface AdvogadoRepository extends JpaRepository<Advogado, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_vinculacao WHERE id_advogado = ?1", nativeQuery = true)
    public void deletarAdvogadoVinculado(Long id);

}
