package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
		PreparedStatement st = null;
		ResultSet rs = null;	
		List<Livro> allBooks = new ArrayList<>();
		
		try {
			st = conn.prepareStatement("SELECT * FROM livro");			
			rs = st.executeQuery();

			while(rs.next()) {
				
				Livro book = new Livro();
				book.setId(rs.getInt("id"));
				book.setAutor(rs.getString("autor"));
				book.setTitulo(rs.getString("titulo"));
				book.setDisponivel(rs.getInt("disponivel"));
				book.setAnoPublicacao(rs.getString("ano_publicacao"));
				
				allBooks.add(book);
			}		
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		return allBooks;
	}

	@Override
	public void insertBook(Livro book) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO livro (autor,titulo,ano_publicacao) VALUES (?,?,?)");
			st.setString(1, book.getAutor());
			st.setString(2,book.getTitulo());
			st.setString(3, book.getAnoPublicacao());
			
			st.executeUpdate();
			
			
		}catch(SQLException e) {
			System.out.println("Erro ao inserir um livro: " + e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteBookByID(Integer id) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("DELETE FROM livro WHERE id = ?");
			st.setInt(1, id);
			
			st.executeUpdate();
			System.out.println("Livro deletado!");
		}catch(SQLException e) {
			System.out.println("Erro ao deletar um livro: " + e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Boolean existeLivro(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		int idLivro = 0;
		
		try {
			st = conn.prepareStatement("SELECT id FROM livro WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if(rs.next()) {
				idLivro = rs.getInt("id");
				
				if (id == idLivro) {
					return true;
				}
				
			}


		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
		return false;
	}

}
