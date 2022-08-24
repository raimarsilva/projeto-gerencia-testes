package com.projeto.teste.util;

import com.projeto.teste.ProjetoTestes.model.Processo;

public class ProcessoUtil {
    // Retorna um processo sem id
    public static Processo processoParaSalvar(){
        return Processo.builder()
                .numero("1-11.2022.1.1.0001")
                .orgaoJudic("TST")
                .relator("Amaury Rodrigues Pinto Junior")
                .build();
    }

    // Retorna um processo com id
    public static Processo processoValido(){
        return Processo.builder()
                .id(1L)
                .numero("1-11.2022.1.1.0001")
                .orgaoJudic("TST")
                .relator("Amaury Rodrigues Pinto Junior")
                .build();
    }
}
