package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import grupo3.fantur.dao.RolDao;
import grupo3.fantur.dao.UsuarioDao;
import grupo3.fantur.model.Rol;
import grupo3.fantur.model.Usuario;

@ManagedBean
@ViewScoped
public class UsuarioBean {

	private Usuario usuario;
	private List<Rol> roles;
	private long rolId;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public long getRolId() {
		return rolId;
	}

	public void setRolId(long rolId) {
		this.rolId = rolId;
	}

	@Inject
	UsuarioDao usuarioDao;

	@Inject
	RolDao rolDao;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		roles = rolDao.findAll();
	}

	// ALTA
	public void createUsuario() {
		Rol rol = rolDao.findById(rolId);
		usuario.setRol(rol);
		usuarioDao.create(usuario);
	}

	// BAJA
	public void deleteUsuario(Usuario usuario) {
		usuarioDao.delete(usuario);
	}

	// MODIFICACION
	public void updateUsuario() {
		Rol rol = rolDao.findById(rolId);
		usuario.setRol(rol);
		usuarioDao.update(usuario);
	}

	/*
	 * Devuelve la lista actual de usuarios
	 * 
	 */
	public List<Usuario> listaUsuarios() {
		return usuarioDao.findAll();
	}

	/*
	 * Lee el usuario actual para editarlo
	 * 
	 */
	public void leer(Usuario u) {
		usuario = u;
	}

}
