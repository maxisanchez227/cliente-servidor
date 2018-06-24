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
	private List<String> rolesId;
	
	public Usuario getUsuario() {
		return usuario;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public List<String> getRolesId() {
		return rolesId;
	}

	public void setRolesId(List<String> rolesId) {
		this.rolesId = rolesId;
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
		for (String rolId : rolesId) {
			long id = Long.parseLong(rolId);
			Rol rol = rolDao.findById(id);
			usuario.getRoles().add(rol);
		}
		usuarioDao.create(usuario);
	}

	// BAJA
	public void deleteUsuario(Usuario usuario) {
		usuarioDao.delete(usuario);
	}

	// MODIFICACION
	public void updateUsuario() {
		usuario.getRoles().clear();
		for (String rolId : rolesId) {
			long id = Long.parseLong(rolId);
			Rol rol = rolDao.findById(id);
			usuario.getRoles().add(rol);
		}
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
	
	/*
	 * Devuelve un String con los nombres de los roles
	 * 
	 */
	public String nombreRoles() {
		List<Rol> nombreRoles = usuario.getRoles();
		String roles = "";
		for(Rol r: nombreRoles) {
			roles += r.getNombre() + " ";
		}
		return roles;
	}

}
