package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import grupo3.fantur.dao.ActividadDao;
import grupo3.fantur.dao.HotelDao;
import grupo3.fantur.dao.PaqueteDao;
import grupo3.fantur.dao.PasajeDao;
import grupo3.fantur.model.Actividad;
import grupo3.fantur.model.Hotel;
import grupo3.fantur.model.Paquete;
import grupo3.fantur.model.Pasaje;

@ManagedBean
@ViewScoped
public class PaqueteBean {

	private Paquete paquete;
	private List<Pasaje> pasajes;
	private List<Hotel> hoteles;
	private List<Actividad> actividades;
	private long pasajeId;
	private long hotelId;
	private long actividadId;

	// GETTERS

	public Paquete getPaquete() {
		return paquete;
	}

	public List<Pasaje> getPasajes() {
		return pasajes;
	}

	public List<Hotel> getHoteles() {
		return hoteles;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	// GETTERS & SETTERS

	public long getPasajeId() {
		return pasajeId;
	}

	public void setPasajeId(long pasajeId) {
		this.pasajeId = pasajeId;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public long getActividadId() {
		return actividadId;
	}

	public void setActividadId(long actividadId) {
		this.actividadId = actividadId;
	}

	@Inject
	PaqueteDao paqueteDao;

	@Inject
	PasajeDao pasajeDao;

	@Inject
	HotelDao hotelDao;

	@Inject
	ActividadDao actividadDao;

	@PostConstruct
	public void init() {
		paquete = new Paquete();
		pasajes = pasajeDao.findAll();
		hoteles = hotelDao.findAll();
		actividades = actividadDao.findAll();
	}

	// ALTA
	public void createPaquete() {
		Pasaje pasaje = pasajeDao.findById(pasajeId);
		Hotel hotel = hotelDao.findById(hotelId);
		Actividad actividad = actividadDao.findById(actividadId);
		paquete.setPasaje(pasaje);
		paquete.setHotel(hotel);
		paquete.setActividad(actividad);
		paqueteDao.create(paquete);
	}

	// BAJA
	public void deletePaquete(Paquete paquete) {
		paqueteDao.delete(paquete);
	}

	// MODIFICACION
	public void updatePaquete() {
		Pasaje pasaje = pasajeDao.findById(pasajeId);
		Hotel hotel = hotelDao.findById(hotelId);
		Actividad actividad = actividadDao.findById(actividadId);
		paquete.setPasaje(pasaje);
		paquete.setHotel(hotel);
		paquete.setActividad(actividad);
		paqueteDao.update(paquete);
	}

	/*
	 * Devuelve la lista actual de paquetes
	 * 
	 */
	public List<Paquete> listaPaquetes() {
		return paqueteDao.findAll();
	}

	/*
	 * Lee el paquete actual para editarlo
	 * 
	 */
	public void leer(Paquete p) {
		paquete = p;
		pasajeId = p.getPasaje().getId();
		hotelId = p.getHotel().getId();
		actividadId = p.getActividad().getId();
	}

}
