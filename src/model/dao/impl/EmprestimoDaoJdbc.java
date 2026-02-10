package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;
import db.DBException;
import model.dao.EmprestimoDAO;
import model.entities.Emprestimo;
import model.entities.enums.StatusEmprestimo;
import model.services.BusinessException;

public class EmprestimoDaoJdbc implements EmprestimoDAO {

	
	public Connection conn = null;
	
	public EmprestimoDaoJdbc(Connection conn) {
		this.conn = conn;
		}
	
	@Override
	public void salvar(Emprestimo emprestimo) {
		PreparedStatement st = null;
		
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
			}else {
				throw new BusinessException("Erro ao concluir o emprestimo.");
			}
			
		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public Emprestimo buscarPorId(Integer id) {
		
		PreparedStatement st = null;
		ResultSet rs = null;
		Emprestimo emp = new Emprestimo();
		
		try {
			st = conn.prepareStatement("SELECT * FROM emprestimo WHERE usuario_id = ? AND status = 'ATIVO'");
			st.setInt(1, id);
			rs = st.executeQuery();
			
			if(rs.next()) {
				java.sql.Date dataInicio = rs.getDate("data_inicio");
				java.sql.Date dataFim = rs.getDate("data_fim");
				emp.setId(rs.getInt("id"));
				emp.setId_livro(rs.getInt("livro_id"));
				emp.setId_usuario(rs.getInt("usuario_id"));
				emp.setDataFim(dataFim.toLocalDate());
				emp.setDataInicio(dataInicio.toLocalDate());
				emp.setStatus(StatusEmprestimo.ATIVO);
			}else{
				throw new BusinessException("Nenhum emprestimo em ativo ou ID inválido.");
			}

		} catch (SQLException e) {
			throw new DBException(e.getMessage());
		}finally {
			DB.closeStatement(st);
		}
		return emp;
	}

	@Override
	public void atualizar(Emprestimo emprestimo) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE emprestimo SET data_fim = ? WHERE id = ?");
			st.setDate(1, java.sql.Date.valueOf(emprestimo.getDataFim()));
			st.setInt(2, emprestimo.getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected == 0) {
				throw new BusinessException("Empréstimo não foi atualizado devido a um erro com o ID.");
			}else {
				System.out.println("Empréstimo atualizado com sucesso! ");
			}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

	@Override
	public void cancelarEmprestimo(Emprestimo emprestimo) {
		PreparedStatement st = null;
		
		try {
			st = conn.prepareStatement("UPDATE emprestimo SET status = ? WHERE id = ?");
			st.setString(1, emprestimo.getStatus().toString());
			st.setInt(2, emprestimo.getId());
			
			int rowsAffected = st.executeUpdate();
			
			if (rowsAffected == 0) {
				throw new BusinessException("Empréstimo não foi atualizado devido a um erro com o ID.");
			}else {
				System.out.println("Empréstimo encerrado com sucesso! ");
			}
		}catch(SQLException e) {
			throw new DBException(e.getMessage());
		}
		finally {
			DB.closeStatement(st);
		}
		
	}

}
