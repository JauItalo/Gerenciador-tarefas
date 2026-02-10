package com.sistematarefas.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.sistematarefas.backend.model.Tarefa;
import com.sistematarefas.backend.repository.TarefaRepository;



@Service
public class TarefaService {
    private final TarefaRepository repository;


    public TarefaService(TarefaRepository repository) {
        this.repository = repository;
    }

    public List<Tarefa> listarTodas() {
        return repository.findAll();
    }

    public Optional<Tarefa> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public Tarefa salvar(Tarefa tarefa) {
        return repository.save(tarefa);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

}
