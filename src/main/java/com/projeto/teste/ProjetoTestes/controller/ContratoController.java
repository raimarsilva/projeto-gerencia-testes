package com.projeto.teste.ProjetoTestes.controller;

import com.projeto.teste.ProjetoTestes.model.Contrato;
import com.projeto.teste.ProjetoTestes.service.ContratoService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


//import java.util.List;

@Controller
@RequestMapping(path = "/")
public class ContratoController {

    @Autowired
    ContratoService contratoService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("contratos", contratoService.listarTodos());
        return "index";
    }
    
    // Rota para salvar
    @PostMapping(path = "/cadastrar")
    public String salvar(@ModelAttribute Contrato contrato){
        contratoService.salvar(contrato);
        return "redirect:/";
    }

    // Rota para listar todos
    @GetMapping(path = "/list")
    public List<Contrato> listarTodos(){
      List<Contrato> contratos = contratoService.listarTodos();
      return contratos;
    }
/*
    // Rota para listar todos
    @GetMapping(path = "/contratos")
    public ResponseEntity<List<Contrato>> listarTodos(){
        return ResponseEntity.status(HttpStatus.OK).body(contratoService.listarTodos());
    }

    // Rota para buscar pelo id
    @GetMapping(path = "/contrato/{id}")
    public ResponseEntity<Contrato> buscarPeloId(@PathVariable Long id){
        Contrato contrato = contratoService.buscarPeloId(id);
        if(contrato != null){
            return ResponseEntity.status(HttpStatus.OK).body(contrato);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(contrato);
        }
    }

    // Rota para Deletar
    @DeleteMapping(path = "/contrato/{id}")
    public ResponseEntity<?> deletarPeloId(@PathVariable Long id){
        if(contratoService.deletarPeloId(id)){
            return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
        }
    }

    // Rota para Atualizar
    @PatchMapping(path = "/contrato/{id}")
    public ResponseEntity<Contrato> atualizar(@PathVariable Long id, @RequestBody Contrato contrato){
        Contrato contratoAtualizado = contratoService.atualizar(id, contrato);
        if(contratoAtualizado != null){
            return ResponseEntity.status(HttpStatus.OK).body(contratoAtualizado);
        }else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(contratoAtualizado);
        }
    }
*/
}
