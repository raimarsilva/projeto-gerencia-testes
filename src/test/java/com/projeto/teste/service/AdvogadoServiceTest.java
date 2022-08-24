package com.projeto.teste.service;

import com.projeto.teste.ProjetoTestes.model.Advogado;
import com.projeto.teste.ProjetoTestes.repository.AdvogadoRepository;
import com.projeto.teste.ProjetoTestes.service.AdvogadoService;
import com.projeto.teste.util.AdvogadoUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

@ExtendWith(SpringExtension.class)
public class AdvogadoServiceTest {

    @InjectMocks
    AdvogadoService advogadoService;

    @Mock
    AdvogadoRepository advogadoRepository;

    @BeforeEach
    void setup(){

        // Quando for executado o findById do repository para o atualizarCliente
        BDDMockito.when(advogadoRepository.findById(ArgumentMatchers.eq(1L)))
                .thenReturn(Optional.ofNullable(AdvogadoUtil.advogadoValido()));

        // Quando for executado o save do repository, esse é executado tanto para salvar e atualizar
        BDDMockito.when(advogadoRepository.save(ArgumentMatchers.any()))
                .thenReturn(AdvogadoUtil.advogadoValido());

        // Quando for executado o save do repository de um advogado com processo vinculado
        BDDMockito.when(advogadoRepository
                        .save(ArgumentMatchers.eq(AdvogadoUtil.advogadoProcessoVinculado())))
                .thenReturn(AdvogadoUtil.advogadoProcessoVinculado());
    }

    @Test
    @DisplayName(value = "Atualizar um advogado com sucesso")
    void atualizarAdvogado_quandoSucesso(){

        Advogado advogadoEsperado = AdvogadoUtil.advogadoParaSalvar();
        // Salvando um cliente
        advogadoEsperado = advogadoService.salvar(advogadoEsperado);

        // Modificando o cliente
        advogadoEsperado.setNome("Ciclano dos Santos");

        // chamando o método que será testado
        Advogado advogadoAtualizado = advogadoService.atualizar(advogadoEsperado.getId(),advogadoEsperado);

        // Verificações
        Assertions.assertNotNull(advogadoAtualizado);
        Assertions.assertEquals(advogadoEsperado.getId(), advogadoAtualizado.getId());
        Assertions.assertEquals(advogadoEsperado.getNome(), advogadoAtualizado.getNome());
        Assertions.assertEquals(advogadoEsperado.getRegistroOAB(), advogadoAtualizado.getRegistroOAB());

    }

    @Test
    @DisplayName(value = "Tentar atualizar um advogado que não existe")
    void atualizarAdvogado_SemSucesso(){

        Advogado advogadoEsperado = AdvogadoUtil.advogadoParaSalvar();
        // Salvando um cliente
        advogadoEsperado = advogadoService.salvar(advogadoEsperado);

        // Modificando o cliente
        advogadoEsperado.setNome("Ciclano dos Santos");

        // chamando o método que será testado com ID de advogado que não existe
        Advogado advogadoAtualizado = advogadoService.atualizar(2L,advogadoEsperado);

        // Verificações
        Assertions.assertNull(advogadoAtualizado);

        Assertions.assertThrows(NullPointerException.class, () -> {
            advogadoAtualizado.getId();
            advogadoAtualizado.getNome();
            advogadoAtualizado.getRegistroOAB();
        });
    }


    @Test
    @DisplayName(value = "Vincular um processo a um advogado com sucesso")
    void vincularProcesso_ComSucesso(){


    }


}
