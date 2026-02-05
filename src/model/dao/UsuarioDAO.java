package model.dao;

import java.util.List;

import model.entities.Usuario;

public interface UsuarioDAO {
	List<Usuario> getAllUser();
	void updateUser(Integer id);
	void addUser(Usuario user);
	void deleteUser(Integer id);
}
