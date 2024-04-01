package com.listaTarefas.model.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;
import com.listaTarefas.Repository.TarefaRepository;
import com.listaTarefas.Repository.UsuarioRepository;
import com.listaTarefas.model.Tarefa;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/tarefa")
public class TarefaController {

	@Autowired
	private TarefaRepository tarefaRepository;

	@Autowired
	private UsuarioRepository usuarioRepository;

	@GetMapping("/id/{idUsuario}")
	public ResponseEntity<List<Tarefa>> getAllById(@PathVariable long idUsuario) {
		return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.findAllByUsuarioId(idUsuario));
	}

	@PostMapping
	public ResponseEntity<Tarefa> post(@Valid @RequestBody Tarefa tarefa) {
		if (usuarioRepository.existsById(tarefa.getUsuario().getId())) {
			return ResponseEntity.status(HttpStatus.CREATED).body(tarefaRepository.save(tarefa));
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USUARIO NÃO EXISTE");

	}

	@PutMapping
	public ResponseEntity<Tarefa> put(@Valid @RequestBody Tarefa tarefa) {
		if (tarefaRepository.existsById(tarefa.getId())) {
			if (tarefaRepository.existsById(tarefa.getUsuario().getId())) {
				return ResponseEntity.status(HttpStatus.OK).body(tarefaRepository.save(tarefa));
			}
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "USUARIO NAO EXISTE");
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TAREFA NÃO EXISTE");
	}

	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		Optional<Tarefa> tarefa = tarefaRepository.findById(id);
		if (tarefa.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "TAREFA NÃO EXISTE");
		}
		tarefaRepository.deleteById(id);
		// Aqui ele está removendo,retorna uma NO_CONTENT 204
		// Aqui não retornamos um reponseEntity pq nao tem motivos pra gente retornar um
		// objeto que foi removido

	}
}
