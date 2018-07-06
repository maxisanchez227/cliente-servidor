package grupo3.fantur.jsf;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;

import grupo3.fantur.model.Hotel;
import grupo3.fantur.ws.JAXRSClient;

@ManagedBean
@ViewScoped
public class HotelBean {

	private Hotel hotel;

	public Hotel getHotel() {
		return hotel;
	}
	
	private WebTarget hotelWebTarget;

	@PostConstruct
	public void init() {
		hotel = new Hotel();
		hotelWebTarget = JAXRSClient.buildHotelClient();
	}

	// ALTA
	public void createHotel() {
		Invocation.Builder invocationBuilder = hotelWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.post(Entity.entity(hotel, MediaType.APPLICATION_JSON));
	}

	// BAJA
	public void deleteHotel(Hotel hotel) {
		String id = String.valueOf(hotel.getId());
		WebTarget deleteWebTarget = hotelWebTarget.path(id);
		Invocation.Builder invocationBuilder = deleteWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.delete();
	}

	// MODIFICACION
	public void updateHotel() {
		Invocation.Builder invocationBuilder = hotelWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.put(Entity.entity(hotel, MediaType.APPLICATION_JSON));
	}

	/*
	 * Devuelve la lista actual de hoteles
	 * 
	 */
	public List<Hotel> listaHoteles() {
		Invocation.Builder invocationBuilder = hotelWebTarget.request(MediaType.APPLICATION_JSON);
		return invocationBuilder.get(new GenericType<List<Hotel>>() {});
	}

	/*
	 * Lee el hotel actual para editarlo
	 * 
	 */
	public void leer(Hotel h) {
		hotel = h;
	}

}
