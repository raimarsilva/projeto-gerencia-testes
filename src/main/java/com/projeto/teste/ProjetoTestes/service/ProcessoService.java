package com.projeto.teste.ProjetoTestes.service;

import com.projeto.teste.ProjetoTestes.model.Processo;
import com.projeto.teste.ProjetoTestes.repository.ProcessoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProcessoService {
	
    @Autowired
    ProcessoRepository processoRepository;
    
    public Processo salvar(Processo processo){
    	return processoRepository.save(processo);
    	
    }

    public List<Processo> listarTodos(){
        return processoRepository.findAll();
    }

    public Processo buscarPeloId(Long id){
        if(processoRepository.findById(id).isPresent()) {

            return processoRepository.findById(id).get();
        }else{
            return null;
        }

    }

    public Processo atualizar(Long id, Processo processo){
        if(processoRepository.findById(id).isPresent()){
            return processoRepository.save(processo);

        }else{
            return null;
        }
    }

    public boolean deletarPeloId(Long id){
        if(processoRepository.findById(id).isPresent()){
            fazerDesvinculacaoProcesso(id);
            processoRepository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }

    // Método para fazer a desvinculação dos processos com advogados
    @Transactional
    public void fazerDesvinculacaoProcesso(Long id){
        processoRepository.deletarProcessoVinculado(id);
    }



}
