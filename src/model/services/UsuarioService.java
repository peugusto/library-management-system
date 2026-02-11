package model.services;

import java.util.List;

import model.dao.DaoFactory;
import model.dao.EmprestimoDAO;
import model.dao.UsuarioDAO;
import model.entities.Emprestimo;
import model.entities.Usuario;
import model.entities.enums.StatusEmprestimo;
import model.entities.enums.StatusUsuario;

public class UsuarioService {

	private UsuarioDAO user = DaoFactory.createUsuarioDAO();
	private EmprestimoDAO emp = DaoFactory.createEmprestimo();
	
	public List<Usuario> retornarUsuarios() {
		return user.getAllUser();
	}

	public void validarCampos(Usuario obj) {
		user.insertUser(obj);
	}
	
	public Usuario retornar(Integer id) {
		return user.getUserById(id);
	}
	
	public void editarEmail(Integer id,String email) throws BusinessException {
		if (id <= 0) {
			throw new BusinessException("ID não pode ser menor que 0");
		}
		else if (email.isEmpty()) {
			throw new BusinessException("Nenhum email foi informado.");	
		}
		else if (!email.contains("@")) {
			throw new BusinessException("Email inválido");
		}
		user.updateUser(id, email);;
	}

	public void validarID(Integer id) throws BusinessException {
		
		if (id <= 0) throw new BusinessException("ID não pode ser menor que 0");
		
		Usuario obj = retornar(id);
		
		Emprestimo e = emp.buscarPorId(obj.getId());
		if(e.getStatus() == StatusEmprestimo.ATIVO ) {
			throw new BusinessException("Nâo é possivel excluir um usuário com emprestimo em ativo");
		}else {
			user.deleteUserById(id);
		}
		
	}
	

	
	public void desativarUsuário(Integer id) {
		
		if (id <= 0) throw new BusinessException("ID não pode ser menor que 0");
		
		Usuario obj = retornar(id);
		
		if(obj.getAtivo() == StatusUsuario.ATIVO) {
			Boolean hasHistory = emp.existeHistorico(obj.getId());
			
			if(hasHistory) {
				throw new BusinessException("Nâo é possivel desativar um usuario que possua um histórico.");
			}else {
				user.encerrarUsuario(id);
			}
		}else {
			throw new BusinessException("O usuário precisa estar ATIVO para poder ser encerrado.");
		}
		
	}
}
