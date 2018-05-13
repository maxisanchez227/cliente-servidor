package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import grupo3.fantur.dao.PasajeDao;
import grupo3.fantur.model.Pasaje;

@ManagedBean
@ViewScoped
public class PasajeBean {

	private Pasaje pasaje;

	public Pasaje getPasaje() {
		return pasaje;
	}

	@Inject
	PasajeDao pasajeDao;

	@PostConstruct
	public void init() {
		pasaje = new Pasaje();
	}

	// ALTA
	public void createPasaje() {
		pasajeDao.create(pasaje);
	}

	// BAJA
	public void deletePasaje(Pasaje pasaje) {
		pasajeDao.delete(pasaje);
	}

	// MODIFICACION
	public void updatePasaje() {
		pasajeDao.update(pasaje);
	}

	/*
	 * Devuelve la lista actual de pasajes
	 * 
	 */
	public List<Pasaje> listaPasajes() {
		return pasajeDao.findAll();
	}

	/*
	 * Lee el pasaje actual para editarlo
	 * 
	 */
	public void leer(Pasaje p) {
		pasaje = p;
	}

}
