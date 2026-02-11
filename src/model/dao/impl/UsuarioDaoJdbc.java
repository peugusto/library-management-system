package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import db.DB;
import db.DBException;
import model.dao.UsuarioDAO;
import model.entities.Usuario;
import model.entities.enums.StatusUsuario;
import model.services.BusinessException;

public class UsuarioDaoJdbc implements UsuarioDAO {

	private Connection conn = null;
	
	public UsuarioDaoJdbc (Connection conn) {
		this.conn = conn;
	}

	@Override
	public List<Usuario> getAllUser() {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		List<Usuario> allUsers = new ArrayList<>();
		try {
			st = conn.prepareStatement("SELECT * FROM usuario");			
			rs = st.executeQuery();

			while(rs.next()) {
				
				Usuario user = new Usuario();				
				user.setId(rs.getInt("id"));
				user.setNome(rs.getString("nome"));
				user.setEmail(rs.getString("email"));
	            user.setAtivo(StatusUsuario.valueOf(rs.getString("status"))); 
				allUsers.add(user);
			}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		return allUsers;
	}

	@Override
	public void updateUser(Integer id,String email) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE usuario SET email = ? WHERE id = ?");	
			st.setString(1, email);
			st.setInt(2, id);
		
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Usuário editado com sucesso.");
			}
			
		}catch(SQLException e) {
			throw new BusinessException("Problema ao editar o email do usuário.");
		}finally {
			DB.closeStatement(st);
		}		
	}

	@Override
	public void insertUser(Usuario user) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO usuario (nome,email,data_cadastro,status) VALUES (?,?,?,?)");
			st.setString(1, user.getNome());
			st.setString(2, user.getEmail());
			st.setDate(3, java.sql.Date.valueOf(user.getDataCadastro()));
			st.setString(4, user.getAtivo().toString());
			
			int rows =st.executeUpdate();
			
			if (rows > 0) {
				System.out.println("Usuário adicionado ao banco de dados.");
			}else {
				throw new BusinessException("Erro ao adicionar usuário ao banco de dados.");
			}
			
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void deleteUserById(Integer id) {
		PreparedStatement st = null;
		
		try {
			
			st = conn.prepareStatement("DELETE FROM usuario WHERE id = ?");
			st.setInt(1, id);
			
			st.executeUpdate();
			
			System.out.println("Usuario deletado! ");
			
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Boolean existeUsuario(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		int idUsuario = 0;
		
		try {
			st = conn.prepareStatement("SELECT id FROM usuario WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				idUsuario = rs.getInt("id");
				
				if (id == idUsuario) {
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
	public Usuario getUserById(Integer id) {
		 	PreparedStatement st = null;
		    ResultSet rs = null;
		    Usuario usuario = null;

		    try {
		        st = conn.prepareStatement("SELECT id, nome, email, data_cadastro, status FROM usuario WHERE id = ?");
		        st.setInt(1, id);

		        rs = st.executeQuery();

		        if (rs.next()) {
		            usuario = new Usuario();
		            usuario.setId(rs.getInt("id"));
		            usuario.setNome(rs.getString("nome"));
		            usuario.setEmail(rs.getString("email"));
		            usuario.setAtivo(StatusUsuario.valueOf(rs.getString("status"))); 

		        }else {
		        	throw new BusinessException("Usuario não existe ou ID inválido.");
		        }

		    } catch (SQLException e) {
				throw new DBException(e.getMessage());
		    } finally {
		        DB.closeStatement(st);

		    }

		    return usuario;
	}

	@Override
	public void encerrarUsuario(Integer id) {
	 	PreparedStatement st = null;
	    
	    try {
	    	
			st = conn.prepareStatement("UPDATE usuario SET status = ? WHERE id = ?");	
			st.setString(1, StatusUsuario.INATIVO.toString());
			st.setInt(2, id);
		
			int rowsAffected = st.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Usuário encerrado com sucesso.");
			}
			
		}catch(SQLException e) {
			throw new BusinessException("Problema ao encerrar o usuário.");
		}finally {
			DB.closeStatement(st);
		}		
		
	}

}
