package com.projeto.teste.ProjetoTestes.service;

import com.projeto.teste.ProjetoTestes.model.Advogado;
import com.projeto.teste.ProjetoTestes.model.Processo;
import com.projeto.teste.ProjetoTestes.repository.AdvogadoRepository;
import com.projeto.teste.ProjetoTestes.repository.ProcessoRepository;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.Set;

@Log4j2
@Service
public class AdvogadoService {

    @Autowired
    AdvogadoRepository advogadoRepository;

    @Autowired
    ProcessoRepository processoRepository;

    
    /**
     * @author Método: Iramar. Teste: Raimar
     * @param advogado
     * @return Retorna um objeto do tipo Advogado.
     */
    @Test
    public Advogado salvar(Advogado advogado){
    	Advogado adv = advogadoRepository.save(advogado);
    	assertEquals(adv, advogado);
        return adv;
    }



    public List<Advogado> listarTodos(){
        return advogadoRepository.findAll();
    }

    public Advogado buscarPeloId(Long id){
        if(advogadoRepository.findById(id).isPresent()) {
            return advogadoRepository.findById(id).get();
        }else{
            return null;
        }

    }

    public Advogado vincularProcesso(Advogado advogado, Processo processo){
        advogado.getProcessos().add(processo);

        return advogadoRepository.save(advogado);
    }

    public Advogado desvincularProcesso(Advogado advogado, Processo processo){
        advogado.getProcessos().remove(processo);

        return advogadoRepository.save(advogado);
    }


    public Advogado atualizar(Long id, Advogado advogado){
        if(advogadoRepository.findById(id).isPresent()){
            return advogadoRepository.save(advogado);

        }else{
            return null;
        }

    }

    public boolean deletarPeloId(Long id){
        if(advogadoRepository.findById(id).isPresent()){
            fazerDesvinculacaoAdvogado(id);
            advogadoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    // Método para fazer a desvinculação dos processos com advogados
    @Transactional
    public void fazerDesvinculacaoAdvogado(Long id){
        advogadoRepository.deletarAdvogadoVinculado(id);
    }

}
