package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import grupo3.fantur.model.Rol;
import grupo3.fantur.ws.JAXRSClient;

@ManagedBean
@ViewScoped
public class RolBean {

	private Rol rol;

	public Rol getRol() {
		return rol;
	}

	private WebTarget rolWebTarget;

	@PostConstruct
	private void init() {
		rol = new Rol();
		rolWebTarget = JAXRSClient.buildClientRolClient();
	}

	// ALTA
	public void createRol() {
		// rolDao.create(rol);
		Invocation.Builder invocationBuilder = rolWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.post(Entity.entity(rol, MediaType.APPLICATION_JSON));
	}

	// BAJA
	public void deleteRol(Rol rol) {
		// rolDao.delete(rol);
		String id = String.valueOf(rol.getId());
		WebTarget deleteRolWebTarget = rolWebTarget.path(id);
		Invocation.Builder invocationBuilder = deleteRolWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.delete();
	}

	// MODIFICACION
	public void updateRol() {
		// rolDao.update(rol);
		Invocation.Builder invocationBuilder = rolWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.put(Entity.entity(rol, MediaType.APPLICATION_JSON));
	}

	/*
	 * Devuelve la lista actual de roles
	 * 
	 */
	public List<Rol> listaRoles() {
		// return rolDao.findAll();
		Invocation.Builder invocationBuilder = rolWebTarget.request(MediaType.APPLICATION_JSON);
		return invocationBuilder.get(new GenericType<List<Rol>>() {});
	}

	/*
	 * Lee el rol actual para editarlo
	 * 
	 */
	public void leer(Rol r) {
		rol = r;
	}

}
