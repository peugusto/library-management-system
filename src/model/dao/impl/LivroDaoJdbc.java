package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DBException;
import model.dao.LivroDAO;
import model.entities.Livro;
import model.services.BusinessException;

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
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		return allBooks;
	}

	@Override
	public void insertBook(Livro book) {
		
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO livro (autor,titulo,ano_publicacao,diponivel) VALUES (?,?,?,?)");
			st.setString(1, book.getAutor());
			st.setString(2,book.getTitulo());
			st.setString(3, book.getAnoPublicacao());
			st.setBoolean(4, book.getDisponivel());
			int rows = st.executeUpdate();
			
			if (rows > 0) {
				System.out.println("Livro adicionado ao banco de dados.");
			}else {
				throw new DBException("Erro ao adicionar o livro no banco de dados.");
			}
			
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
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
			throw new DBException(e.getMessage());
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
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
		return false;
	}

	@Override
	public Livro getBookByID(Integer id) {
		
	    PreparedStatement st = null;
	    ResultSet rs = null;
	    Livro livro = null;
	    
	    try {
	        st = conn.prepareStatement(
	            "SELECT id, titulo, autor, ano_publicacao, disponivel FROM livro WHERE id = ?"
	        );
	        st.setInt(1, id);

	        rs = st.executeQuery();

	        if (rs.next()) {
	            livro = new Livro();
	            livro.setId(rs.getInt("id"));
	            livro.setTitulo(rs.getString("titulo"));
	            livro.setAutor(rs.getString("autor"));
	            livro.setAnoPublicacao(rs.getString("ano_publicacao"));
	            livro.setDisponivel(rs.getInt("disponivel")); 
	            
	        }else {
				throw new BusinessException("Nenhum livro encontrado ou ID inválido.");
	        }

	    } catch (SQLException e) {
			throw new DBException(e.getMessage());
	    } finally {
	        DB.closeStatement(st);
	    }
		return livro;
	}

}
