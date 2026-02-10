package model.dao;

import java.util.List;

import model.entities.Usuario;

public interface UsuarioDAO {
	List<Usuario> getAllUser();
	void updateUser(Integer id,String email);
	void insertUser(Usuario user);
	Usuario getUserById(Integer id);
	void deleteUserById(Integer id);
	Boolean existeUsuario(Integer id);
}