package com.projeto.teste.ProjetoTestes.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@ToString
// Anotações lombok
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
// JPA
@Entity
@Table(name = "tb_advogado")
public class Advogado {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private String nome;
  private String registroOAB;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "tb_vinculacao", joinColumns = @JoinColumn(name = "id_advogado", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "id_processo", referencedColumnName = "id"))
  // @JsonManagedReference(value = "JsonManagedReference")
  // @JsonBackReference(value = "JsonBackReference")
  Set<Processo> processos;

}
