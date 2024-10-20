package todo.TodoList.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import todo.TodoList.model.Tarefa;
import todo.TodoList.repository.TarefaRepository;

import java.util.List;
import java.util.Optional;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class TarefaService {

    @Autowired
    private TarefaRepository tarefaRepository;

    //Listar todas as Tarefas
    public List<Tarefa> getAllTarefas() {
        return tarefaRepository.findAll();
    }


    //Criar Tarefas
    public Tarefa createTarefa(Tarefa tarefa) {
        return tarefaRepository.save(tarefa);
    }

    //Deletar Tarefas
    public void deleteTarefa(Long id){
        tarefaRepository.deleteById(id);
    }

    // Editar (atualizar) Tarefa
    public Tarefa updateTarefa(Long id, Tarefa tarefaAtualizada) {
        Optional<Tarefa> tarefaExistente = tarefaRepository.findById(id);

        if (tarefaExistente.isPresent()) {
            Tarefa tarefa = tarefaExistente.get();
            tarefa.setText(tarefaAtualizada.getText());
            tarefa.setCategory(tarefaAtualizada.getCategory());
            tarefa.setIsCompleted(tarefaAtualizada.isIsCompleted());
            return tarefaRepository.save(tarefa);
        } else {
            throw new RuntimeException("Tarefa com ID " + id + " não encontrada");
        }
    }



}
