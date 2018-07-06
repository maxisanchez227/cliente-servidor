package grupo3.fantur.ws;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import grupo3.fantur.dao.PaqueteDao;
import grupo3.fantur.model.Paquete;

@Path("/paquete")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class PaqueteResource {

	@Inject
	private PaqueteDao paqueteDao;

	@GET
	public Response getPaquetes() {
		List<Paquete> paquetes = paqueteDao.findAll();
		return Response.ok(paquetes).build();
	}

	@GET
	@Path("/{paqueteId}")
	public Response getPaquete(@PathParam("paqueteId") long id) {
		Paquete paquete = paqueteDao.findById(id);
		return Response.ok(paquete).build();
	}
	
	@POST
	public void addPaquete(Paquete paquete) {
		paqueteDao.create(paquete);
	}

	@PUT
	public void updatePaquete(Paquete paquete) {
		paqueteDao.update(paquete);
	}

	@DELETE
	@Path("/{paqueteId}")
	public void deletePaquete(@PathParam("paqueteId") long id) {
		paqueteDao.delete(paqueteDao.findById(id));
	}

}
