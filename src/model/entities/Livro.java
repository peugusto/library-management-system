package model.entities;
public class Livro {
	private Integer id;
	private String titulo;
	private String autor;
	private String anoPublicacao;
	private Boolean disponivel;
	
	public Livro() {}
	
	public Livro(String titulo, String autor, String anoPublicacao) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.anoPublicacao = anoPublicacao;
		this.disponivel = true;
	}
	public Integer getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public String getAnoPublicacao() {
		return anoPublicacao;
	}
	public void setAnoPublicacao(String anoPublicacao) {
		this.anoPublicacao = anoPublicacao;
	}
	public Boolean getDisponivel() {
		return disponivel;
	}
	public void setDisponivel(int d) {
		if (d == 0) {
			this.disponivel = false;
			return;
		}
		this.disponivel = true;
	}
	
	
}
