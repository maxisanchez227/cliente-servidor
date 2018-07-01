package grupo3.fantur.ws;

import java.util.Random;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/organismo")
@Produces({MediaType.APPLICATION_JSON})
public class OrganismoResource {
	
	@GET
	public Response get() {
		Random r = new Random();
		int n = r.nextInt(100);
		return Response.ok(n).build();
	}

}
