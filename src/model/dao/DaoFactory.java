package model.dao;

import db.DB;
import model.dao.impl.UsuarioDaoJdbc;

public class DaoFactory {
	
	
	public static UsuarioDAO createUsuarioDAO() {
		return new UsuarioDaoJdbc(DB.getConnection());
	}	

}
