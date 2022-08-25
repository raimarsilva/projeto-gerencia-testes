package com.projeto.teste.service;

import java.util.Optional;

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

import com.projeto.teste.ProjetoTestes.model.Processo;
import com.projeto.teste.ProjetoTestes.repository.ProcessoRepository;
import com.projeto.teste.ProjetoTestes.service.ProcessoService;
import com.projeto.teste.util.ProcessoUtil;

@ExtendWith(SpringExtension.class)
class ProcessoServiceTest {
	@InjectMocks
    ProcessoService processoService;

    @Mock
    ProcessoRepository processoRepository;
	
    @BeforeEach
    void setup(){
		// Quando for executado o findById do repository para o deletarPeloId.
	    BDDMockito.when(processoRepository.findById(ArgumentMatchers.eq(1L)))
	            .thenReturn(Optional.ofNullable(ProcessoUtil.processoValido()));
	    
	 // Quando for executado o save do repository, esse é executado tanto para salvar e atualizar processo.
        BDDMockito.when(processoRepository.save(ArgumentMatchers.any()))
                .thenReturn(ProcessoUtil.processoValido());
        
        
        }
    
	/**
	 * @author Raimar Silva de Lima
	 */
	@Test
	@DisplayName(value = "Salvar um processo pelo ID com sucesso.")
	void testSalvarProcessoComSucesso() {
		// cria um objeto do tipo Processo com ID.
    	Processo processoEsperado = ProcessoUtil.processoValido();
    	
    	// retorna o processo que foi salvo no banco.
    	Processo processoSalvo = processoService.salvar(processoEsperado);
    	
    	// Testes para o processo que foi salvo.
    	Assertions.assertNotNull(processoSalvo);
    	Assertions.assertEquals(processoEsperado, processoSalvo);
	}
	
	/**
	 * @author Raimar Silva de Lima
	 */
	@Test
	@DisplayName(value = "Salvar um processo pelo ID com falha.")
	void testSalvarProcessoFalho() {
		// cria um objeto do tipo Processo com ID.
    	Processo processoEsperado = ProcessoUtil.processoValido();
    	
    	// retorna um processo nulo que foi tentado ser salvo no banco.
    	Processo processoSalvo = processoService.salvar(null);
    	
    	// Testes para o processo que foi salvo.
    	Assertions.assertEquals(processoEsperado, processoSalvo);
    	
	}

	/**
	 * @author Raimar Silva de Lima
	 */
//	@Test
//	@DisplayName(value = "Deletar um processo pelo ID com sucesso.")
//	void testDeletarPeloId() {
//		// cria um objeto do tipo Processo com ID.
//    	Processo processoEsperado = ProcessoUtil.processoValido();
//
//    	boolean foiDeletado = processoService.deletarPeloId(processoEsperado.getId());
//
//    	// Testes para o processo que foi deletado.
//    	Assertions.assertTrue(foiDeletado);
//	}
//
	/**
	 * @author Raimar Silva de Lima
<<<<<<< HEAD
	 */
	@Test
	@DisplayName(value = "Deletar um processo pelo ID com id inválido.")
	void testDeletarPeloIdFalho() {
		// cria um objeto do tipo Processo SEM ID.
    	Processo processoEsperado = ProcessoUtil.processoSemID();
    	
    	boolean foiDeletado = processoService.deletarPeloId(processoEsperado.getId());
    	
    	// Testes para o processo que foi deletado sem Id válido.
    	Assertions.assertFalse(foiDeletado);
	}
//	 */
//	@Test
//	@DisplayName(value = "Deletar um processo pelo ID com id inválido.")
//	void testDeletarPeloIdFalho() {
//		// cria um objeto do tipo Processo SEM ID.
//    	Processo processoEsperado = ProcessoUtil.processoSemID();
//
//    	boolean foiDeletado = processoService.deletarPeloId(processoEsperado.getId());
//
//    	// Testes para o processo que foi deletado sem Id válido.
//    	Assertions.assertFalse(foiDeletado);
//    	Assertions.fail("Informe um processo válido");

}
