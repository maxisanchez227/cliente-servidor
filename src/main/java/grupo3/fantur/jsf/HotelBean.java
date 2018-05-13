package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;

import grupo3.fantur.dao.HotelDao;
import grupo3.fantur.model.Hotel;

@ManagedBean
@ViewScoped
public class HotelBean {
	
	private Hotel hotel;

	public Hotel getHotel() {
		return hotel;
	}

	@Inject
	HotelDao hotelDao;

	@PostConstruct
	public void init() {
		hotel = new Hotel();
	}

	// ALTA
	public void createHotel() {
		hotelDao.create(hotel);
	}

	// BAJA
	public void deleteHotel(Hotel hotel) {
		hotelDao.delete(hotel);
	}

	// MODIFICACION
	public void updateHotel() {
		hotelDao.update(hotel);
	}

	/*
	 * Devuelve la lista actual de hoteles
	 * 
	 */
	public List<Hotel> listaHoteles() {
		return hotelDao.findAll();
	}

	/*
	 * Lee el hotel actual para editarlo
	 * 
	 */
	public void leer(Hotel h) {
		hotel = h;
	}

}
