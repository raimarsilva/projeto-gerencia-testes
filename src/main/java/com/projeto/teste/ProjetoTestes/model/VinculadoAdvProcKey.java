package com.projeto.teste.ProjetoTestes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

// Classe que fará o mapeamento de chaves primárias
@Data
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class VinculadoAdvProcKey implements Serializable {

    @Column(name = "advogado_id")
    private Long advogadoId;

    @Column(name = "advogado_id")
    private Long processoId;

}
