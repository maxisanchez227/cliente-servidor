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

import grupo3.fantur.dao.UsuarioDao;
import grupo3.fantur.model.Usuario;

@Path("/usuario")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class UsuarioResource {

	@Inject
	private UsuarioDao usuarioDao;

	@GET
	public Response getUsuarios() {
		List<Usuario> usuarios = usuarioDao.findAll();
		return Response.ok(usuarios).build();
	}

	@GET
	@Path("/{usuarioId}")
	public Response getUsuario(@PathParam("usuarioId") long id) {
		Usuario usuario = usuarioDao.findById(id);
		return Response.ok(usuario).build();
	}
	
	@POST
	public void addUsuario(Usuario usuario) {
		usuarioDao.create(usuario);
	}

	@PUT
	public void updateUsuario(Usuario usuario) {
		usuarioDao.update(usuario);
	}

	@DELETE
	@Path("/{rolId}")
	public void deleteUsuario(@PathParam("rolId") long id) {
		usuarioDao.delete(usuarioDao.findById(id));
	}

}
