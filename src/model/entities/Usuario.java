package model.entities;

import java.time.LocalDate;

public class Usuario {
	private Integer id;
	private String nome;
	private String email;
	private LocalDate dataCadastro;
	private Boolean ativo;
	
	public Usuario() {
		
	}
	
	public Usuario(String nome, String email) {
		
	    if (nome == null || nome.trim().isEmpty()) {
	        throw new IllegalArgumentException("Nome não pode ser null ou vazio");
	    }

	    if (nome.matches("\\d+")) {
	        throw new IllegalArgumentException("Nome inválido — não pode ser apenas números");
	    }

	    if (email == null || email.trim().isEmpty()) {
	        throw new IllegalArgumentException("Email não pode ser null ou vazio");
	    }

	    if (!email.matches("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,}$")) {
	        throw new IllegalArgumentException("Email inválido");
	    }
		this.nome = nome;
		this.email = email;
		this.dataCadastro = LocalDate.now();
		this.ativo = true;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Integer getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public LocalDate getDataCadastro() {
		return dataCadastro;
	}
	public void setDataCadastro(LocalDate dataCadastro) {
		this.dataCadastro = dataCadastro;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(int ativo) {
		if (ativo == 0) {
			this.ativo = false;
			return;
		}
		this.ativo = true;
	}
	
}
