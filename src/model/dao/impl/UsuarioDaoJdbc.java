package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import model.dao.UsuarioDAO;
import model.entities.Usuario;

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
				user.setAtivo(rs.getInt("ativo"));
				
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
	public void updateUser(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUser(Usuario user) {
		PreparedStatement st = null;

		
		try {
			
			st = conn.prepareStatement("INSERT INTO usuario (nome,email,data_cadastro,ativo) VALUES (?,?,?,?)");
			st.setString(1, user.getNome());
			st.setString(2, user.getEmail());
			st.setDate(3, java.sql.Date.valueOf(user.getDataCadastro()));
			st.setBoolean(4, user.getAtivo());
			
			st.executeUpdate();
			
			
		}catch(SQLException e) {
			e.printStackTrace();
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
			System.out.println("Erro ao deletar um usuário: " + e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

}
