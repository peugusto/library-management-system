package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import db.DB;
import model.dao.LivroDAO;
import model.entities.Livro;

public class LivroDaoJdbc implements LivroDAO{

	public Connection conn = null;
	
	public LivroDaoJdbc(Connection conn) {
		this.conn = conn;
		}
	@Override
	public List<Livro> getAllBooks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateBook(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertBook(Livro book) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO livro (autor,titulo,ano_publicacao) VALUES (?,?,?)");
			st.setString(1, book.getAutor());
			st.setString(2,book.getTitulo());
			st.setDate(3, java.sql.Date.valueOf(book.getAnoPublicacao()));
			
			st.executeUpdate();
			
			
		}catch(SQLException e) {
			System.out.println("Erro ao inserir um livro: " + e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

	@Override
	public void deleteBookByID(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM livro WHERE id = ?");
			st.setInt(1, id);
			
			st.executeUpdate();
			
		}catch(SQLException e) {
			System.out.println("Erro ao deletar um livro: " + e.getMessage());
		}finally {
			DB.closeStatement(st);
			DB.closeConnection();
		}
		
	}

}
