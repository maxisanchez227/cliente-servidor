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

import grupo3.fantur.model.Actividad;
import grupo3.fantur.ws.JAXRSClient;

@ManagedBean
@ViewScoped
public class ActividadBean {

	private Actividad actividad;

	public Actividad getActividad() {
		return actividad;
	}

	private WebTarget actividadWebTarget;

	@PostConstruct
	public void init() {
		actividad = new Actividad();
		actividadWebTarget = JAXRSClient.buildActividadClient();
	}

	// ALTA
	public void createActividad() {
		Invocation.Builder invocationBuilder = actividadWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.post(Entity.entity(actividad, MediaType.APPLICATION_JSON));
	}

	// BAJA
	public void deleteActividad(Actividad actividad) {
		String id = String.valueOf(actividad.getId());
		WebTarget deleteWebTarget = actividadWebTarget.path(id);
		Invocation.Builder invocationBuilder = deleteWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.delete();
	}

	// MODIFICACION
	public void updateActividad() {
		Invocation.Builder invocationBuilder = actividadWebTarget.request(MediaType.APPLICATION_JSON);
		invocationBuilder.put(Entity.entity(actividad, MediaType.APPLICATION_JSON));
	}

	/*
	 * Devuelve la lista actual de actividades
	 * 
	 */
	public List<Actividad> listaActividades() {
		Invocation.Builder invocationBuilder = actividadWebTarget.request(MediaType.APPLICATION_JSON);
		return invocationBuilder.get(new GenericType<List<Actividad>>() {
		});
	}

	/*
	 * Lee la actividad actual para editarlo
	 * 
	 */
	public void leer(Actividad a) {
		actividad = a;
	}

}
