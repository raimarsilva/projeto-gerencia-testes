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
@Table(name = "tb_advogado")
public class Advogado {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String registroOAB;

    @OneToMany(mappedBy = "advogado")
    Set<VinculadoAdvProc> vinculacoes;

}
