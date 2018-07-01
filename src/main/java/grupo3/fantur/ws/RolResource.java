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

import grupo3.fantur.dao.RolDao;
import grupo3.fantur.model.Rol;

@Path("/rol")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class RolResource {

	@Inject
	private RolDao rolDao;

	@GET
	public Response getRoles() {
		List<Rol> roles = rolDao.findAll();
		return Response.ok(roles).build();
	}

	@GET
	@Path("/{rolId}")
	public Response getRol(@PathParam("rolId") long id) {
		Rol rol = rolDao.findById(id);
		return Response.ok(rol).build();
	}

	@POST
	public void addRol(Rol rol) {
		rolDao.create(rol);
	}
	
//	@PUT
//	@Path("/{rolId}")
//	public void updateRol(@PathParam("rolId") long id, Rol rol) {
//		rol.setId(id);
//		rolDao.update(rol);
//	}
	
	@PUT
	public void updateRol(Rol rol) {
		rolDao.update(rol);
	}

	@DELETE
	@Path("/{rolId}")
	public void deleteRol(@PathParam("rolId") long id) {
		rolDao.delete(rolDao.findById(id));
	}

}
