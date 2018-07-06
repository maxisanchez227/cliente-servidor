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

import grupo3.fantur.dao.ReservaDao;
import grupo3.fantur.model.Reserva;

@Path("/reserva")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class ReservaResource {

	@Inject
	private ReservaDao reservaDao;

	@GET
	public Response getReservas() {
		List<Reserva> reservas = reservaDao.findAll();
		return Response.ok(reservas).build();
	}

	@GET
	@Path("/{reservaId}")
	public Response getReserva(@PathParam("reservaId") long id) {
		Reserva reserva = reservaDao.findById(id);
		return Response.ok(reserva).build();
	}
	
	@POST
	public void addReserva(Reserva reserva) {
		reservaDao.create(reserva);
	}

	@PUT
	public void updateReserva(Reserva reserva) {
		reservaDao.update(reserva);
	}

	@DELETE
	@Path("/{reservaId}")
	public void deleteReserva(@PathParam("reservaId") long id) {
		reservaDao.delete(reservaDao.findById(id));
	}

}
