package grupo3.fantur.jsf;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import grupo3.fantur.dao.PaqueteDao;
import grupo3.fantur.dao.ReservaDao;
import grupo3.fantur.dao.UsuarioDao;
import grupo3.fantur.mail.Mail;
import grupo3.fantur.model.Paquete;
import grupo3.fantur.model.Reserva;
import grupo3.fantur.model.Usuario;

@ManagedBean
@ViewScoped
public class ReservaBean {

	private Reserva reserva;
	private List<Usuario> usuarios;
	private List<Paquete> paquetes;
	private long usuarioId;
	private long paqueteId;

	public Reserva getReserva() {
		return reserva;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public List<Paquete> getPaquetes() {
		return paquetes;
	}

	public long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(long usuarioId) {
		this.usuarioId = usuarioId;
	}

	public long getPaqueteId() {
		return paqueteId;
	}

	public void setPaqueteId(long paqueteId) {
		this.paqueteId = paqueteId;
	}
	
	@Inject
	ReservaDao reservaDao;

	@Inject
	UsuarioDao usuarioDao;

	@Inject
	PaqueteDao paqueteDao;
	
	private Client client;
	private WebTarget webTarget;
	
	@PostConstruct
	public void init() {
		reserva = new Reserva();
		usuarios = usuarioDao.findAll();
		paquetes = paqueteDao.findAll();
		
		client = ClientBuilder.newClient();
		webTarget = client.target("http://localhost:8080/fantur/resources");
	}

	// ALTA
	public void createReserva() {
		Usuario usuario = usuarioDao.findById(usuarioId);
		Paquete paquete = paqueteDao.findById(paqueteId);
		reserva.setUsuario(usuario);
		reserva.setPaquete(paquete);
		reservaDao.create(reserva);
	}

	public void reservar(Paquete paquete) {
		FacesContext context = FacesContext.getCurrentInstance();
		WebTarget organismoWebTarget = webTarget.path("/organismo");
		Invocation.Builder invocationBuilder = organismoWebTarget.request(MediaType.APPLICATION_JSON);
		int n = invocationBuilder.get(Integer.class);
		if (n < 50) {
			reserva.setFechaReserva(new Date());
			reserva.setEstado("Reservado");
			reserva.setPaquete(paquete);
			Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
			reserva.setUsuario(usuario);
			reservaDao.create(reserva);
			
			int cant = paquete.getCantidadDisponible();
			cant--;
			paquete.setCantidadDisponible(cant);
			paqueteDao.update(paquete);
			
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reserva realizada", "Reserva realizada con éxito " + n));
			context.getExternalContext().getFlash().setKeepMessages(true);
		} else {
			context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Intente más tarde " + n));
			context.getExternalContext().getFlash().setKeepMessages(true);
		}
	}

	// BAJA
	public void deleteReserva(Reserva Reserva) {
		reservaDao.delete(Reserva);
	}
	
	public void cancelarReserva(Reserva r) {
		Paquete paquete = r.getPaquete();
		int cant = paquete.getCantidadDisponible();
		cant++;
		paquete.setCantidadDisponible(cant);
		paqueteDao.update(paquete);
		reservaDao.delete(r);
		// notifiacion
		Mail mail = new Mail();
		Usuario usuario = (Usuario) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("usuario");
		String direcciones = usuario.getEmail();
		String asunto = "Actialización Estado Reserva";
		String mensaje = "Reserva cancelada";
		mail.sendMsg(direcciones, asunto, mensaje);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Reserva cancelada", "Reserva cancelada con éxito"));
	}

	// MODIFICACION
	public void updateReserva() {
		Usuario usuario = usuarioDao.findById(usuarioId);
		Paquete paquete = paqueteDao.findById(paqueteId);
		reserva.setUsuario(usuario);
		reserva.setPaquete(paquete);
		reservaDao.update(reserva);
	}

	/*
	 * Devuelve la lista actual de reservas
	 * 
	 */
	public List<Reserva> listaReservas() {
		return reservaDao.findAll();
	}

	/*
	 * Devuelve las reservas del usuario logueado
	 * 
	 */
	public List<Reserva> listaMisReservas() {
		List<Reserva> misReservas = new ArrayList<Reserva>();
		List<Reserva> reservas = reservaDao.findAll();
		FacesContext context = FacesContext.getCurrentInstance();
		Usuario usuario = (Usuario) context.getExternalContext().getSessionMap().get("usuario");
		long id = usuario.getId();
		for (Reserva r : reservas) {
			if (r.getUsuario().getId() == id) {
				misReservas.add(r);
			}
		}
		return misReservas;
	}

	/*
	 * Lee la reserva actual para editarlo
	 * 
	 */
	public void leer(Reserva r) {
		reserva = r;
		usuarioId = r.getUsuario().getId();
		paqueteId = r.getPaquete().getId();
	}

}
