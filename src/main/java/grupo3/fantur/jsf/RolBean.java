package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import grupo3.fantur.dao.RolDao;
import grupo3.fantur.model.Rol;

@ManagedBean
@ViewScoped
public class RolBean {

	private Rol rol;

	public Rol getRol() {
		return rol;
	}

	@Inject
	RolDao rolDao;

	@PostConstruct
	private void init() {
		rol = new Rol();
	}

	// ALTA
	public void createRol() {
		rolDao.create(rol);
	}

	// BAJA
	public void deleteRol(Rol rol) {
		rolDao.delete(rol);
	}

	// MODIFICACION
	public void updateRol() {
		rolDao.update(rol);
	}

	/*
	 * Devuelve la lista actual de roles
	 * 
	 */
	public List<Rol> listaRoles() {
		return rolDao.findAll();
	}

	/*
	 * Lee el rol actual para editarlo
	 * 
	 */
	public void leer(Rol r) {
		rol = r;
	}

}
