package grupo3.fantur.jsf;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

import grupo3.fantur.dao.MedioDePagoDao;
import grupo3.fantur.dao.PagoDao;
import grupo3.fantur.dao.ReservaDao;
import grupo3.fantur.mail.Mail;
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
	
	public void pagarReserva(Reserva r) {
		r.setEstado("Pagado");
		reservaDao.update(r);
		MedioDePago medioDePago = medioDepagoDao.findById(medioDePagoId);
		pago.setReserva(r);
		pago.setMedioDePago(medioDePago);
		pago.setFechaPago(new Date());
		pago.setImporte(r.getPaquete().getPrecio());
		pagoDao.create(pago);
		// notificacion
		Mail mail = new Mail();
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		String direcciones = usuario.getEmail();
		String asunto = "Actialización Estado Reserva";
		String mensaje = "Reserva pagada";
		mail.sendMsg(direcciones, asunto, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Pago realizado", "Reserva pagada con éxito"));
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
	 * Devuelve fecha y medio de pago de las reservas pagadas
	 * 
	 */
	public Date getFechaPago(Reserva r) {
		Date fechaPago = null;
		long reservaId = r.getId();
		List<Pago> pagos = pagoDao.findAll();
		for(Pago p: pagos) {
			if(reservaId == p.getReserva().getId()) {
				fechaPago = p.getFechaPago();
			}
		}
		return fechaPago;
	}
	
	public String getMedioDePago(Reserva r) {
		String mdp = null;
		long reservaId = r.getId();
		List<Pago> pagos = pagoDao.findAll();
		for(Pago p: pagos) {
			if(reservaId == p.getReserva().getId()) {
				mdp = p.getMedioDePago().getNombre();
			}
		}
		return mdp;
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
