package com.projeto.teste.ProjetoTestes.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class VinculadoAdvProc {

    @EmbeddedId
    private VinculadoAdvProcKey id;

    @ManyToOne
    @MapsId("advogadoId")
    @JoinColumn(name = "id_advogado")
    private Advogado advogado;

    @ManyToOne
    @MapsId("processoId")
    @JoinColumn(name = "id_processo")
    private Processo processo;

    private LocalDateTime dataCriacao;

}
