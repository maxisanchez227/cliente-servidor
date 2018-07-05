package grupo3.fantur.ws;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;

public class JAXRSClient {

	public static final String WS_ENDPOINT = "http://localhost:8080/fantur/resources";

	private static WebTarget builtTarget() {
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target(WS_ENDPOINT);
		return target;
	}
	
	public static WebTarget buildClientRolClient() {
		WebTarget target = builtTarget();
		return target.path("/rol");
		
	}
	
	public static WebTarget buildUsuarioClient() {
		WebTarget target = builtTarget();
		return target.path("/usuario");
	}
}
