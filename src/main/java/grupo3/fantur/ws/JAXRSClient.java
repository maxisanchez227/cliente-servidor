package grupo3.fantur.ws;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;

public class JAXRSClient {

	public static final String WS_ENDPOINT = "http://localhost:8080/fantur/resources";
	public Client client;
	public WebTarget webTarget;
	public Invocation.Builder invocationBuilder;

}
