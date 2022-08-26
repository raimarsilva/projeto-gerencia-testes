package com.projeto.teste.ProjetoTestes.repository;

import com.projeto.teste.ProjetoTestes.model.Processo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ProcessoRepository extends JpaRepository<Processo, Long> {

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM tb_vinculacao WHERE id_processo = ?1", nativeQuery = true)
    public void deletarProcessoVinculado(Long id);

}
