package grupo3.fantur.ws;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class JAXRSClient {

	public static final String WS_ENDPOINT = "http://localhost:8080/fantur/resources";

	private static WebTarget buildTarget() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(WS_ENDPOINT);
		return target;
	}
	
	public static WebTarget buildRolClient() {
		WebTarget target = buildTarget();
		return target.path("/rol");
		
	}
	
	public static WebTarget buildUsuarioClient() {
		WebTarget target = buildTarget();
		return target.path("/usuario");
	}
	
	public static WebTarget buildPasajeClient() {
		WebTarget target = buildTarget();
		return target.path("/pasaje");
	}
	
	public static WebTarget buildHotelClient() {
		WebTarget target = buildTarget();
		return target.path("/hotel");
	}
	
	public static WebTarget buildActividadClient() {
		WebTarget target = buildTarget();
		return target.path("/actividad");
	}
	
	public static WebTarget buildPaqueteClient() {
		WebTarget target = buildTarget();
		return target.path("/reserva");
	}
	
	public static WebTarget buildReservaClient() {
		WebTarget target = buildTarget();
		return target.path("/reserva");
	}
}
