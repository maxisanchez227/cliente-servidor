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

import grupo3.fantur.dao.PasajeDao;
import grupo3.fantur.model.Pasaje;

@Path("/pasaje")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class PasajeResource {

	@Inject
	private PasajeDao pasajeDao;

	@GET
	public Response getPasajes() {
		List<Pasaje> pasajes = pasajeDao.findAll();
		return Response.ok(pasajes).build();
	}

	@GET
	@Path("/{pasajeId}")
	public Response getPasaje(@PathParam("pasajeId") long id) {
		Pasaje pasaje = pasajeDao.findById(id);
		return Response.ok(pasaje).build();
	}

	@POST
	public void addPasaje(Pasaje pasaje) {
		pasajeDao.create(pasaje);
	}

	@PUT
	public void updatePasaje(Pasaje pasaje) {
		pasajeDao.update(pasaje);
	}

	@DELETE
	@Path("/{pasajeId}")
	public void deletePasaje(@PathParam("pasajeId") long id) {
		pasajeDao.delete(pasajeDao.findById(id));
	}

}
