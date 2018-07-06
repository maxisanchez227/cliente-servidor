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

import grupo3.fantur.model.Pasaje;
import grupo3.fantur.ws.JAXRSClient;

@ManagedBean
@ViewScoped
public class PasajeBean {

	private Pasaje pasaje;

	public Pasaje getPasaje() {
		return pasaje;
	}
	
	private WebTarget pasajeWebTarget;

	@PostConstruct
	public void init() {
		pasaje = new Pasaje();
		pasajeWebTarget = JAXRSClient.buildPasajeClient();
	}

	// ALTA
	public void createPasaje() {
		Invocation.Builder invocationBuilder = pasajeWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.post(Entity.entity(pasaje, MediaType.APPLICATION_JSON));
	}

	// BAJA
	public void deletePasaje(Pasaje pasaje) {
		String id = String.valueOf(pasaje.getId());
		WebTarget deleteWebTarget = pasajeWebTarget.path(id);
		Invocation.Builder invocationBuilder = deleteWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.delete();
	}

	// MODIFICACION
	public void updatePasaje() {
		Invocation.Builder invocationBuilder = pasajeWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.put(Entity.entity(pasaje, MediaType.APPLICATION_JSON));
	}

	/*
	 * Devuelve la lista actual de pasajes
	 * 
	 */
	public List<Pasaje> listaPasajes() {
		Invocation.Builder invocationBuilder = pasajeWebTarget.request(MediaType.APPLICATION_JSON);
		return invocationBuilder.get(new GenericType<List<Pasaje>>() {});
	}

	/*
	 * Lee el pasaje actual para editarlo
	 * 
	 */
	public void leer(Pasaje p) {
		pasaje = p;
	}

}
