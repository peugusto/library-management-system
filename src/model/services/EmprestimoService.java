package model.services;

import model.dao.DaoFactory;
import model.dao.EmprestimoDAO;
import model.dao.LivroDAO;
import model.dao.UsuarioDAO;
import model.entities.Emprestimo;

public class EmprestimoService {

	
	private EmprestimoDAO emprestimo = DaoFactory.createEmprestimo();
	private LivroDAO livro = DaoFactory.createLivroDAO();
	private UsuarioDAO usuario = DaoFactory.createUsuarioDAO();
	
	
	public void salvarEmprestimo(int idLivro, int idUsuario) {
		
			Boolean existeUsuario = usuario.existeUsuario(idUsuario);
			Boolean existeLivro = livro.existeLivro(idLivro);
			
			if (existeUsuario && existeLivro) {
				Emprestimo emp = new Emprestimo(idLivro,idUsuario);
				emprestimo.salvar(emp);
			}else {
				throw new BusinessException("Livro ou Usuario não existente");
			}
	}
}
