package com.projeto.teste.util;

import com.projeto.teste.ProjetoTestes.model.Advogado;

import java.util.HashSet;

public class AdvogadoUtil {
    // Retorna um advogado sem id
    public static Advogado advogadoParaSalvar() {
        return Advogado.builder()
                .nome("Fulano da Silva")
                .registroOAB("UF999999")
                .processos(new HashSet<>())
                .build();
    }

    // Retorna um advogado com id
    public static Advogado advogadoValido() {

        return Advogado.builder()
                .id(1L)
                .nome("Fulano da Silva")
                .registroOAB("UF999999")
                .processos(new HashSet<>())
                .build();
    }

    // Retorna um advogado com id e um processo vinculado
    public static Advogado advogadoProcessoVinculado() {

        Advogado advogado = Advogado.builder()
                .id(1L)
                .nome("Fulano da Silva")
                .registroOAB("UF999999")
                .processos(new HashSet<>())
                .build();

        advogado.getProcessos().add(ProcessoUtil.processoValido());
        return advogado;
    }

}