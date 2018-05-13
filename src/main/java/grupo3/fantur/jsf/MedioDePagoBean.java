package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import grupo3.fantur.dao.MedioDePagoDao;
import grupo3.fantur.model.MedioDePago;

@ManagedBean
@ViewScoped
public class MedioDePagoBean {
	
	private MedioDePago medioDePago;

	public MedioDePago getMedioDePago() {
		return medioDePago;
	}

	@Inject
	MedioDePagoDao medioDePagoDao;

	@PostConstruct
	private void init() {
		medioDePago = new MedioDePago();
	}

	// ALTA
	public void createMedioDePago() {
		medioDePagoDao.create(medioDePago);
	}

	// BAJA
	public void deleteMedioDePago(MedioDePago medioDePago) {
		medioDePagoDao.delete(medioDePago);
	}

	// MODIFICACION
	public void updateMedioDePago() {
		medioDePagoDao.update(medioDePago);
	}

	/*
	 * Devuelve la lista actual de medios de pago
	 * 
	 */
	public List<MedioDePago> listaMediosDePago() {
		return medioDePagoDao.findAll();
	}

	/*
	 * Lee el rol actual para editarlo
	 * 
	 */
	public void leer(MedioDePago mp) {
		medioDePago = mp;
	}

}
