package com.listaTarefas.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "tb_usuario")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;

	@NotBlank(message = "O nome do usuário deve ser informado")
	private String nome;
	@NotBlank(message = "o username deve ser informado")
	private String usuario;

	@Email(message = "Formato de e-mail inválido")
	@NotBlank(message = "O e-mail do usuário deve ser informado")
	private String email;
	@NotBlank(message = "A senha deve ser informada")
	@Size(min = 6, message = "A senha DEVE possuir mais de 6 caracteres")
	private String senha;
	private String foto;

	@OneToMany(fetch=FetchType.LAZY,mappedBy="usuario",cascade=CascadeType.REMOVE)
	@JsonIgnoreProperties("usuario")
	private List<Tarefa> tarefa;

	public Usuario(Long id, @NotBlank(message = "O nome do usuário deve ser informado") String nome,
			@NotBlank(message = "o username deve ser informado") String usuario,
			@Email(message = "Formato de e-mail inválido") @NotBlank(message = "O e-mail do usuário deve ser informado") String email,
			@NotBlank(message = "A senha deve ser informada") @Size(min = 6, message = "A senha DEVE possuir mais de 6 caracteres") String senha,
			String foto, List<Tarefa> tarefa) {
		super();
		Id = id;
		this.nome = nome;
		this.usuario = usuario;
		this.email = email;
		this.senha = senha;
		this.foto = foto;
		this.tarefa = tarefa;
	}

	public Usuario() {
		super();
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getFoto() {
		return foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public List<Tarefa> getTarefa() {
		return tarefa;
	}

	public void setTarefa(List<Tarefa> tarefa) {
		this.tarefa = tarefa;
	}
	

}
