package grupo3.fantur.jsf;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import grupo3.fantur.dao.MedioDePagoDao;
import grupo3.fantur.dao.PagoDao;
import grupo3.fantur.dao.ReservaDao;
import grupo3.fantur.model.MedioDePago;
import grupo3.fantur.model.Pago;
import grupo3.fantur.model.Reserva;
import grupo3.fantur.model.Usuario;

@ManagedBean
@ViewScoped
public class PagoBean {

	private Pago pago;
	private List<Reserva> reservas;
	private List<MedioDePago> mediosDePago;
	private long reservaId;
	private long medioDePagoId;

	public Pago getPago() {
		return pago;
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public List<MedioDePago> getMediosDePago() {
		return mediosDePago;
	}

	public long getReservaId() {
		return reservaId;
	}

	public void setReservaId(long reservaId) {
		this.reservaId = reservaId;
	}

	public long getMedioDePagoId() {
		return medioDePagoId;
	}

	public void setMedioDePagoId(long medioDePagoId) {
		this.medioDePagoId = medioDePagoId;
	}

	@Inject
	PagoDao pagoDao;

	@Inject
	ReservaDao reservaDao;

	@Inject
	MedioDePagoDao medioDepagoDao;

	@PostConstruct
	private void init() {
		pago = new Pago();
		reservas = reservaDao.findAll();
		mediosDePago = medioDepagoDao.findAll();
	}

	// ALTA
	public void createPago() {
		Reserva reserva = reservaDao.findById(reservaId);
		MedioDePago medioDePago = medioDepagoDao.findById(medioDePagoId);
		pago.setReserva(reserva);
		pago.setMedioDePago(medioDePago);
		pagoDao.create(pago);
	}

	// BAJA
	public void deletePago(Pago pago) {
		pagoDao.delete(pago);
	}

	// MODIFICACION
	public void updatePago() {
		pagoDao.update(pago);
	}

	/*
	 * Devuelve la lista actual de pagos
	 * 
	 */
	public List<Pago> listaPagos() {
		return pagoDao.findAll();
	}

	/*
	 * Lee el pago actual para editarlo
	 * 
	 */
	public void leer(Pago p) {
		pago = p;
		reservaId = p.getReserva().getId();
		medioDePagoId = p.getMedioDePago().getId();
	}

}
