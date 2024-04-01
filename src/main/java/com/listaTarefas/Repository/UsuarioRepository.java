package com.listaTarefas.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.listaTarefas.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long>{

}
