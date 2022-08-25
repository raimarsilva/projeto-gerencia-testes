package com.projeto.teste.service;

import com.projeto.teste.ProjetoTestes.model.Advogado;
import com.projeto.teste.ProjetoTestes.model.Processo;
import com.projeto.teste.ProjetoTestes.repository.AdvogadoRepository;
import com.projeto.teste.ProjetoTestes.service.AdvogadoService;
import com.projeto.teste.util.AdvogadoUtil;
import com.projeto.teste.util.ProcessoUtil;
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
public class AdvogadoIdTest {
    
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
    @DisplayName(value = "Buscar um advogado pelo id ")
    void BuscaIdAdvogado(){
        
        //preparando cenário
        Advogado advogadoTeste = AdvogadoUtil.advogadoValido();
        advogadoTeste = advogadoService.salvar(advogadoTeste);


        // chamando o método que será testado
        Advogado advogadoBuscado = advogadoService.buscarPeloId(1L);

        // chamando o método que será testado com ID de advogado que não existe
        Advogado advogadoNulo = advogadoService.buscarPeloId(2L);

        // Verificações
        Assertions.assertEquals(advogadoTeste, advogadoBuscado);

        Assertions.assertNull(advogadoNulo);

    }
}
