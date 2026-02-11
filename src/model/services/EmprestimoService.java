package model.services;

import model.dao.DaoFactory;
import model.dao.EmprestimoDAO;
import model.dao.LivroDAO;
import model.dao.UsuarioDAO;
import model.entities.Emprestimo;
import model.entities.Livro;
import model.entities.Usuario;
import model.entities.enums.StatusLivro;
import model.entities.enums.StatusUsuario;

public class EmprestimoService {

	
	private EmprestimoDAO emprestimo = DaoFactory.createEmprestimo();
	private LivroDAO livro = DaoFactory.createLivroDAO();
	private UsuarioDAO usuario = DaoFactory.createUsuarioDAO();
	
	
	public void salvarEmprestimo(int idLivro, int idUsuario) {
		
			
			Boolean existeUsuario = usuario.existeUsuario(idUsuario);
			Boolean existeLivro = livro.existeLivro(idLivro);
			
			if (existeUsuario && existeLivro) {
				Usuario u = usuario.getUserById(idUsuario);
				Livro l = livro.getBookByID(idLivro);
				
				if(u.getAtivo() == StatusUsuario.ATIVO && l.getDisponivel() == StatusLivro.DISPONIVEL) {
					Emprestimo emp = new Emprestimo(idLivro,idUsuario);
					emprestimo.salvar(emp);
				}else {
					throw new BusinessException("Livro ou Usuario indisponivel para empréstimo");
				}
				

			}else {
				throw new BusinessException("Livro ou Usuario não existente");
			}
	}
	public Emprestimo retornarEmprestimo(int id) {
		if (id <= 0) {
			throw new BusinessException("ID não pode ser 0 ou menor.");
		}
		
		Emprestimo emp = emprestimo.buscarPorId(id);
		return emp;
	}
	
	public void atualizarEmprestimo(Emprestimo obj) {
		emprestimo.atualizar(obj);
	}
	public void cancelarEmprestimo(Emprestimo obj) {
		emprestimo.cancelarEmprestimo(obj);
	}
}
