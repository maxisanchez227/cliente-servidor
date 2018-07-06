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

import grupo3.fantur.dao.PagoDao;
import grupo3.fantur.model.Pago;

@Path("/pago")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class PagoResource {

	@Inject
	private PagoDao pagoDao;

	@GET
	public Response getPagos() {
		List<Pago> pagos = pagoDao.findAll();
		return Response.ok(pagos).build();
	}

	@GET
	@Path("/{pagoId}")
	public Response getPago(@PathParam("pagoId") long id) {
		Pago pago = pagoDao.findById(id);
		return Response.ok(pago).build();
	}
	
	@POST
	public void addPago(Pago pago) {
		pagoDao.create(pago);
	}

	@PUT
	public void updatePago(Pago rol) {
		pagoDao.update(rol);
	}

	@DELETE
	@Path("/{pagoId}")
	public void deletePago(@PathParam("pagoId") long id) {
		pagoDao.delete(pagoDao.findById(id));
	}

}
