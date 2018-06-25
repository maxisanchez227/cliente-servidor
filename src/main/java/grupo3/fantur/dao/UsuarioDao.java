package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.Usuario;

@Local
public interface UsuarioDao {

	void create(Usuario usuario);

	Usuario findById(Long id);

	void update(Usuario usuario);

	void delete(Usuario usuario);

	List<Usuario> findAll();
	
	Usuario iniciarSesion(Usuario usuario);

}
