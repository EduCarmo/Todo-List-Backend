package todo.TodoList.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import todo.TodoList.model.Tarefa;
import todo.TodoList.service.TarefaService;

import java.util.List;

@RestController
@RequestMapping("/api/tarefas")
public class TarefaController {

    @Autowired
    private TarefaService tarefaService;

    @GetMapping("/listarTarefa")
    public List<Tarefa> getAllTarefas() {
        return tarefaService.getAllTarefas();
    }

    @PostMapping("/criarTarefa")
    public ResponseEntity<Tarefa> createTarefa(@RequestBody Tarefa tarefa){
        Tarefa newTarefa = tarefaService.createTarefa(tarefa);
        return new ResponseEntity<>(newTarefa, HttpStatus.CREATED);
    }

    @DeleteMapping("/deletarTarefa/{id}")
    public ResponseEntity<Void> deleteTarefa(@PathVariable Long id){
        tarefaService.deleteTarefa(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/editarTarefa/{id}")
    public ResponseEntity<Tarefa> updateTarefa(@PathVariable Long id, @RequestBody Tarefa tarefa) {
        try {
            Tarefa tarefaAtualizada = tarefaService.updateTarefa(id, tarefa);
            return ResponseEntity.ok(tarefaAtualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }


}
