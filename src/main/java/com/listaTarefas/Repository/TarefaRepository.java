package com.listaTarefas.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.listaTarefas.model.Tarefa;

public interface TarefaRepository extends JpaRepository<Tarefa, Long> {

	public List<Tarefa> findAllByUsuarioId(@Param("usuario") Long id);
}
