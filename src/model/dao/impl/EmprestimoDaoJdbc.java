package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import model.dao.EmprestimoDAO;
import model.entities.Emprestimo;

public class EmprestimoDaoJdbc implements EmprestimoDAO {

	
	public Connection conn = null;
	
	public EmprestimoDaoJdbc(Connection conn) {
		this.conn = conn;
		}
	
	@Override
	public void salvar(Emprestimo emprestimo) {
		PreparedStatement st = null;
		ResultSet rs = null;
		
		try {
			st = conn.prepareStatement("INSERT INTO emprestimo (livro_id,usuario_id,data_inicio,data_fim,status) VALUES (?,?,?,?,?)");
			st.setInt(1, emprestimo.getId_livro());
			st.setInt(2, emprestimo.getId_usuario());
			st.setDate(3, java.sql.Date.valueOf(emprestimo.getDataInicio()));
			st.setDate(4, java.sql.Date.valueOf(emprestimo.getDataFim()));
			st.setString(5, emprestimo.getStatus().toString());
			
			int row = st.executeUpdate();
			
			if(row > 0) {
				System.out.println("Empréstimo concluído com sucesso.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Emprestimo buscarPorId(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void atualizar(Emprestimo emprestimo) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cancelarEmprestimo(Emprestimo emprestimo) {
		// TODO Auto-generated method stub
		
	}

}
