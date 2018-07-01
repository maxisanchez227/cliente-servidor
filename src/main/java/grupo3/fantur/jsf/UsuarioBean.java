package grupo3.fantur.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import grupo3.fantur.dao.RolDao;
import grupo3.fantur.dao.UsuarioDao;
import grupo3.fantur.model.Rol;
import grupo3.fantur.model.Usuario;
import grupo3.fantur.ws.JAXRSClient;

@ManagedBean
@ViewScoped
public class UsuarioBean extends JAXRSClient {

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
	
	WebTarget usuarioWebTarget;

	@Inject
	UsuarioDao usuarioDao;

	@Inject
	RolDao rolDao;

	@PostConstruct
	public void init() {
		usuario = new Usuario();
		roles = rolDao.findAll();
		client = ClientBuilder.newClient();
		webTarget = client.target(WS_ENDPOINT);
		usuarioWebTarget = webTarget.path("/usuario");
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

	public void registrar() {
		Rol rol = null;
		List<Rol> roles = rolDao.findAll();
		for (Rol r : roles) {
			if (r.getNombre().equals("Cliente")) {
				rol = r;
			}
		}
		usuario.getRoles().add(rol);
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
		// return usuarioDao.findAll();
		invocationBuilder = usuarioWebTarget.request(MediaType.APPLICATION_JSON);
		return invocationBuilder.get(new GenericType<List<Usuario>>() {});
	}

	/*
	 * Lee el usuario actual para editarlo
	 * 
	 */
	public void leer(Usuario u) {
		usuario = u;
	}

	/*
	 * esAdmin // esCliente
	 * 
	 */
	public static boolean esAdmin(Usuario u) {
		boolean admin = false;
		List<Rol> roles = u.getRoles();
		for (Rol r : roles) {
			if (r.getNombre().equals("Administrador")) {
				admin = true;
			}
		}
		return admin;
	}

	public static boolean esCliente(Usuario u) {
		boolean cliente = false;
		List<Rol> roles = u.getRoles();
		for (Rol r : roles) {
			if (r.getNombre().equals("Cliente")) {
				cliente = true;
			}
		}
		return cliente;
	}

	/*
	 * Inicia sesion con el usuario si existe
	 * 
	 */
	public String iniciarSesion() {
		Usuario u;
		String redireccion = null;
		try {
			u = usuarioDao.iniciarSesion(usuario);
			// Almacenar en la sesion de JSF --> put(alias,objeto)
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", u);
			if (u != null) {
				if (esAdmin(u)) {
					redireccion = "protected/home.xhtml?faces-redirect=true";
				} else {
					redireccion = "home.xhtml?faces-redirect=true;";
				}
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso",
						"Cuenta inexistente o contraseña incorrecta"));
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(FacesMessage.SEVERITY_FATAL, "Aviso", "Error"));
		}
		return redireccion;
	}

}
