package model.entities;

import java.time.LocalDate;

public class Livro {
	private Integer id;
	private String titulo;
	private String autor;
	private LocalDate anoPublicacao;
	private Boolean disponivel;
	
	public Livro(String titulo, String autor, LocalDate anoPublicacao) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.anoPublicacao = anoPublicacao;
		this.disponivel = true;
	}
	public Integer getId() {
		return id;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getAutor() {
		return autor;
	}
	public void setAutor(String autor) {
		this.autor = autor;
	}
	public LocalDate getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(LocalDate anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public Boolean getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(Boolean disponivel) {
		this.disponivel = disponivel;
	}
	
	
}
