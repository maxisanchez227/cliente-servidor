package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import grupo3.fantur.dao.ActividadDao;
import grupo3.fantur.model.Actividad;

@ManagedBean
@ViewScoped
public class ActividadBean {

	private Actividad actividad;

	public Actividad getActividad() {
		return actividad;
	}

	@Inject
	ActividadDao actividadDao;

	@PostConstruct
	public void init() {
		actividad = new Actividad();
	}

	// ALTA
	public void createActividad() {
		actividadDao.create(actividad);
	}

	// BAJA
	public void deleteActividad(Actividad actividad) {
		actividadDao.delete(actividad);
	}

	// MODIFICACION
	public void updateActividad() {
		actividadDao.update(actividad);
	}

	/*
	 * Devuelve la lista actual de actividades
	 * 
	 */
	public List<Actividad> listaActividades() {
		return actividadDao.findAll();
	}

	/*
	 * Lee la actividad actual para editarlo
	 * 
	 */
	public void leer(Actividad a) {
		actividad = a;
	}

}
