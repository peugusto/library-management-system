package model.services;

import model.dao.DaoFactory;
import model.dao.LivroDAO;

public class LivroService {
	
	private LivroDAO livro = DaoFactory.createLivroDAO();

	public void validateID(Integer id) throws BusinessException{
		if (id <= 0) {
			throw new BusinessException("ID não pode ser menor que 0");
		}
		
}
	}
 