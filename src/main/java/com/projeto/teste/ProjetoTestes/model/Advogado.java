package com.projeto.teste.ProjetoTestes.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@ToString
// Anotações lombok
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@JsonIdentityInfo(generator=ObjectIdGenerators.PropertyGenerator.class, property="id")
@Entity
@Table(name = "tb_advogado")
public class Advogado{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String registroOAB;


    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "tb_vinculacao", joinColumns = @JoinColumn(name = "id_advogado",
            referencedColumnName = "id"),
    inverseJoinColumns = @JoinColumn(name = "id_processo", referencedColumnName = "id"))
//    @JsonManagedReference(value = "JsonManagedReference")
//    @JsonBackReference(value = "JsonBackReference")
    Set<Processo> processos;


}
