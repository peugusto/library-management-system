package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.UsuarioDAO;
import model.entities.Usuario;

public class UsuarioService {

	private UsuarioDAO user = DaoFactory.createUsuarioDAO();

	public List<Usuario> retornarUsuarios() {
		return user.getAllUser();
	}

	public void validarCampos(Usuario obj) {

		if (obj.getNome() == null || obj.getNome().trim().isEmpty()) {
			throw new BusinessException("Nenhum nome foi informado.");
		}
		if (!obj.getEmail().contains("@")) {
			throw new BusinessException("Email inválido.");
		}

		user.insertUser(obj);
	}
	
	public void editarEmail(Integer id,String email) throws BusinessException {
		if (id <= 0) {
			throw new BusinessException("ID não pode ser menor que 0");
		}
		if (email.isEmpty()) {
			throw new BusinessException("Nenhum email foi informado.");	
		}
		if (!email.contains("@")) {
			throw new BusinessException("Email inválido");
		}
		

		user.updateUser(id, email);;
	}

	public void validarID(Integer id) throws BusinessException {
		if (id <= 0) {
			throw new BusinessException("ID não pode ser menor que 0");
		}

		user.deleteUserById(id);
	}
}
