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
        
        // Quando for executado o save falho do repository.
        BDDMockito.when(advogadoRepository.save(ArgumentMatchers.eq(null)))
                .thenReturn(null);

        // Quando for executado o save do repository de um advogado com processo vinculado
        BDDMockito.when(advogadoRepository
                        .save(ArgumentMatchers.eq(AdvogadoUtil.advogadoProcessoVinculado())))
                .thenReturn(AdvogadoUtil.advogadoProcessoVinculado());
    }
    
    
    @Test
    @DisplayName(value = "Salvar um advogado com sucesso")
    public void salvarAdvogadoQuandoValido() {
    	// cria um objeto do tipo Advogado.
    	Advogado advogadoEsperado = AdvogadoUtil.advogadoValido();
    	
    	// retorna o advogado que foi salvo no banco.
    	Advogado advogadoSalvo = advogadoService.salvar(advogadoEsperado);
    	
    	// Testes para o advogado que foi salvo.
    	Assertions.assertNotNull(advogadoSalvo);
    	Assertions.assertEquals(advogadoEsperado.getId(), advogadoSalvo.getId());
        Assertions.assertEquals(advogadoEsperado.getNome(), advogadoSalvo.getNome());
        Assertions.assertEquals(advogadoEsperado.getRegistroOAB(), advogadoSalvo.getRegistroOAB());
    }
    
    /**
     * @author Raimar Silva
     */
    @Test
    @DisplayName(value = "Salvar um advogado COM FALHA.")
    public void salvarAdvogadoFalho() {
    	// Teste para o advogado que falhou ao tentar salvar.
    	Assertions.assertNull(advogadoService.salvar(null));
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

        // Preparando o cenário
        Advogado advogado = AdvogadoUtil.advogadoValido();
        Processo processo = ProcessoUtil.processoValido();

        // Chamando método
        advogado = advogadoService.vincularProcesso(advogado, processo);

        // Verificações
        Assertions.assertFalse(advogado.getProcessos().isEmpty());
        Assertions.assertEquals(1, advogado.getProcessos().size());
        Assertions.assertTrue(advogado.getProcessos().contains(processo));
        // processo vinculado
        Processo processoVinculado = advogado.getProcessos().iterator().next();
        Assertions.assertEquals(processo.getId(), processoVinculado.getId() );
        Assertions.assertEquals(processo.getNumero(), processoVinculado.getNumero() );
        Assertions.assertEquals(processo.getRelator(), processoVinculado.getRelator() );
        Assertions.assertEquals(processo.getOrgaoJudic(), processoVinculado.getOrgaoJudic() );

    }


    @Test
    @DisplayName(value = "Desvincular um processo de um advogado com sucesso")
    void desvincularProcesso_ComSucesso(){
        // Preparando o cenário
        Advogado advogado = AdvogadoUtil.advogadoProcessoVinculado();
        // Pegando o processo que desejo desvincular
        Processo processo = advogado.getProcessos().iterator().next();

        // Chamando método
        advogado = advogadoService.desvincularProcesso(advogado, processo);

        // Verificações
        Assertions.assertTrue(advogado.getProcessos().isEmpty());
        Assertions.assertEquals(0, advogado.getProcessos().size());
        Assertions.assertFalse(advogado.getProcessos().contains(processo));
    }

    @Test
    @DisplayName(value = "Buscar um advogado pelo id ")
    void buscarIdAdvogado(){
        
        //preparando cenário
        Advogado advogadoTeste = AdvogadoUtil.advogadoValido();    

        // chamando o método que será testado
        Advogado advogadoBuscado = advogadoService.buscarPeloId(1L);

        // chamando o método que será testado com ID de advogado que não existe
        Advogado advogadoNulo = advogadoService.buscarPeloId(2L);

        // Verificações
        Assertions.assertEquals(advogadoTeste, advogadoBuscado);
        Assertions.assertNull(advogadoNulo);

    }

    @Test
    @DisplayName(value = "Deletar um advogado pelo id com sucesso ")
    void DeletarIdAdvogado(){

        Advogado advogadoTeste = AdvogadoUtil.advogadoValido();

        Boolean foiDeletado = advogadoService.deletarPeloId(advogadoTeste.getId());

        // Testes para o processo que foi deletado.
    	Assertions.assertTrue(foiDeletado);

    }

    @Test
	@DisplayName(value = "Deletar um advogado pelo ID com id inválido.")
	void testDeletarPeloIdFalho() {

    	boolean foiDeletado = advogadoService.deletarPeloId(null);

    	// Testes para o processo que foi deletado.
    	Assertions.assertFalse(foiDeletado);
	}

}
