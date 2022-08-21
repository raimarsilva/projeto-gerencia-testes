package com.projeto.teste.ProjetoTestes.controller;

import com.projeto.teste.ProjetoTestes.model.Advogado;
import com.projeto.teste.ProjetoTestes.service.AdvogadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class AdvogadoController {

    @Autowired
    AdvogadoService advogadoService;

    // Rota para salvar
    @PostMapping(path = "/advogado")
    public ResponseEntity<Advogado> salvar(@RequestBody Advogado advogado){
        return ResponseEntity.status(HttpStatus.CREATED).body(advogadoService.salvar(advogado));
    }

    // Rota para listar todos
    @GetMapping(path = "/advogados")
    public ResponseEntity<List<Advogado>> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(advogadoService.listarTodos());
    }

    // Rota para buscar pelo id
    @GetMapping(path = "/advogado/{id}")
    public ResponseEntity<Advogado> buscarPeloId(@PathVariable Long id){
        Advogado advogado = advogadoService.buscarPeloId(id);
        if(advogado != null){
            return ResponseEntity.status(HttpStatus.OK).body(advogado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(advogado);
        }
    }

    // Rota para Deletar
    @DeleteMapping(path = "/advogado/{id}")
    public ResponseEntity<?> deletarPeloId(@PathVariable Long id){
        if(advogadoService.deletarPeloId(id)){
            return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
    }

    // Rota para Atualizar
    @PatchMapping(path = "/advogado/{id}")
    public ResponseEntity<Advogado> atualizar(@PathVariable Long id, @RequestBody Advogado advogado){
        Advogado advogadoAtualizado = advogadoService.atualizar(id, advogado);
        if(advogadoAtualizado != null){
            return ResponseEntity.status(HttpStatus.OK).body(advogadoAtualizado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(advogadoAtualizado);
        }
    }



}
