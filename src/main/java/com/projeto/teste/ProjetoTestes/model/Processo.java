package com.projeto.teste.ProjetoTestes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

// Anotações lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
// Anotações JPA
@Entity
@Table(name = "tb_processo")
public class Processo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String numero;
    private String orgaoJudic;
    private String relator;

    @OneToMany(mappedBy = "processo")
    Set<VinculadoAdvProc> vinculacoes;

}
