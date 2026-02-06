package model.dao;

import java.util.List;

import model.entities.Usuario;

public interface UsuarioDAO {
	List<Usuario> getAllUser();
	void updateUser(Integer id);
	void insertUser(Usuario user);
	void deleteUserById(Integer id);
}
