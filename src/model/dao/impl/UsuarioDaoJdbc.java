package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateUser(Integer id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void insertUser(Usuario user) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
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
			DB.closeConnection();
		}
		
	}

	@Override
	public void deleteUser(Integer id) {
		// TODO Auto-generated method stub
		
	}

}
