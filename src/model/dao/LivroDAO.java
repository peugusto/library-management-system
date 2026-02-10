package model.dao;

import java.util.List;

import model.entities.Livro;


public interface LivroDAO {
	List<Livro> getAllBooks();
	void insertBook(Livro book);
	Livro getBookByID(Integer id);
	void deleteBookByID(Integer id);
	Boolean existeLivro(Integer id);
}
