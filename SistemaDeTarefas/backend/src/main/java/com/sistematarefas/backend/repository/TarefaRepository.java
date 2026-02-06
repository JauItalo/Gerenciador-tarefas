package com.sistematarefas.backend.repository;

import com.sistematarefas.backend.model.Tarefa;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

}
