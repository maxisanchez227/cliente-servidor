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

import grupo3.fantur.dao.ActividadDao;
import grupo3.fantur.model.Actividad;

@Path("/actividad")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class ActividadResource {

	@Inject
	private ActividadDao actividadDao;

	@GET
	public Response getActividades() {
		List<Actividad> actividades = actividadDao.findAll();
		return Response.ok(actividades).build();
	}

	@GET
	@Path("/{actividadId}")
	public Response getActividad(@PathParam("actividadId") long id) {
		Actividad actividad = actividadDao.findById(id);
		return Response.ok(actividad).build();
	}
	
	@POST
	public void addActividad(Actividad actividad) {
		actividadDao.create(actividad);
	}

	@PUT
	public void updateActividad(Actividad actividad) {
		actividadDao.update(actividad);
	}

	@DELETE
	@Path("/{actividadId}")
	public void deleteActividad(@PathParam("actividadId") long id) {
		actividadDao.delete(actividadDao.findById(id));
	}

}
