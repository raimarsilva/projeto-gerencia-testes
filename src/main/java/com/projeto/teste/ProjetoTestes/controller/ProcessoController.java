package com.projeto.teste.ProjetoTestes.controller;

import com.projeto.teste.ProjetoTestes.model.Processo;
import com.projeto.teste.ProjetoTestes.service.ProcessoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class ProcessoController {

    @Autowired
    ProcessoService processoService;

    // Rota para salvar
    @PostMapping(path = "/processo")
    public ResponseEntity<Processo> salvar(@RequestBody Processo processo){
        return ResponseEntity.status(HttpStatus.CREATED).body(processoService.salvar(processo));
    }

    // Rota para listar todos
    @GetMapping(path = "/processos")
    public ResponseEntity<List<Processo>> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(processoService.listarTodos());
    }

    // Rota para buscar pelo id
    @GetMapping(path = "/processo/{id}")
    public ResponseEntity<Processo> buscarPeloId(@PathVariable Long id){
        Processo processo = processoService.buscarPeloId(id);
        if(processo != null){
            return ResponseEntity.status(HttpStatus.OK).body(processo);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(processo);
        }
    }

    // Rota para Deletar
    @DeleteMapping(path = "/processo/{id}")
    public ResponseEntity<?> deletarPeloId(@PathVariable Long id){
        if(processoService.deletarPeloId(id)){
            return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
    }

    // Rota para Atualizar
    @PatchMapping(path = "/processo/{id}")
    public ResponseEntity<Processo> atualizar(@PathVariable Long id, @RequestBody Processo processo){
        Processo processoAtualizado = processoService.atualizar(id, processo);
        if(processoAtualizado != null){
            return ResponseEntity.status(HttpStatus.OK).body(processoAtualizado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(processoAtualizado);
        }
    }

}
