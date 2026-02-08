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
		super();
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
