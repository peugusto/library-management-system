package model.dao;

import java.util.List;

import model.entities.Livro;


public interface LivroDAO {
	List<Livro> getAllBooks();
	void updateBook(Integer id);
	void insertBook(Livro book);
	void deleteBookByID(Integer id);

}
