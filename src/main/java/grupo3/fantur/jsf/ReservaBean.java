package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import grupo3.fantur.dao.PaqueteDao;
import grupo3.fantur.dao.ReservaDao;
import grupo3.fantur.dao.UsuarioDao;
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
	
	@PostConstruct
	public void init() {
		reserva = new Reserva();
		usuarios = usuarioDao.findAll();
		paquetes = paqueteDao.findAll();
	}

	// ALTA
	public void createReserva() {
		Usuario usuario = usuarioDao.findById(usuarioId);
		Paquete paquete = paqueteDao.findById(paqueteId);
		reserva.setUsuario(usuario);
		reserva.setPaquete(paquete);
		reservaDao.create(reserva);
	}

	// BAJA
	public void deleteReserva(Reserva Reserva) {
		reservaDao.delete(Reserva);
	}

	// MODIFICACION
	public void updateReserva() {
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
	 * Lee la reserva actual para editarlo
	 * 
	 */
	public void leer(Reserva r) {
		reserva = r;
		usuarioId = r.getUsuario().getId();
		paqueteId = r.getPaquete().getId();
	}

}
