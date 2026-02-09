package model.dao;

import db.DB;
import model.dao.impl.EmprestimoDaoJdbc;
import model.dao.impl.LivroDaoJdbc;
import model.dao.impl.UsuarioDaoJdbc;

public class DaoFactory {
	
	
	public static UsuarioDAO createUsuarioDAO() {
		return new UsuarioDaoJdbc(DB.getConnection());
	}	
	
	public static LivroDAO createLivroDAO() {
		return new LivroDaoJdbc(DB.getConnection());
	}
	
	public static EmprestimoDAO createEmprestimo() {
		return new EmprestimoDaoJdbc(DB.getConnection());
	}
}
