package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.LivroDAO;
import model.entities.Livro;
import model.entities.enums.StatusLivro;

public class LivroService {
	
	private LivroDAO livro = DaoFactory.createLivroDAO();

	public void deletarLivro(Integer id){
		if (id <= 0) {
			throw new BusinessException("ID não pode ser menor que 0");
		}
		Livro l = retornar(id);
		
		if(l.getDisponivel() == StatusLivro.DISPONIVEL) {
			livro.deleteBookByID(id);	
		}else {
			throw new BusinessException("Não é possível exlcuir um livro que esteja em empréstimo ativo");
		}

    }
	
	public List<Livro> retonarLivros(){
		return livro.getAllBooks();
	}
	
	public void adicionarLivro(Livro obj) {
		
		if(obj.getAutor().isEmpty()) {
			throw new BusinessException("Autor não pode ser vazio");
		}else if(obj.getTitulo().isBlank()) {
			throw new BusinessException("Autor não pode ser vazio");
		}else if(obj.getAnoPublicacao().isEmpty()) {
			throw new BusinessException("Data não pode ser vazio");
		}else if(obj.getAnoPublicacao().toString().length() != 4) {
			throw new BusinessException("Data inválida");
		}
		
		livro.insertBook(obj);
	}
	
	public Livro retornar(Integer id) {
		if (id <= 0) {
			throw new BusinessException("ID não pode ser menor que 0");
		}
		return livro.getBookByID(id);
	}
	
}
 