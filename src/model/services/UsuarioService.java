package model.services;

import model.dao.DaoFactory;
import model.dao.UsuarioDAO;
import model.entities.Usuario;

public class UsuarioService {
	
	private UsuarioDAO user = DaoFactory.createUsuarioDAO();
	
	
	public void validar(Usuario obj) {
		
		
		
	    if (obj.getNome() == null || obj.getNome().trim().isEmpty()) {
	        throw new BusinessException("Nenhum nome foi informado.");
	    }
	    if (!obj.getEmail().contains("@")) {
	        throw new BusinessException("Email inválido.");
	    }

	    user.insertUser(obj);
	}
}
