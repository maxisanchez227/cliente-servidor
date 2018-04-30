package grupo3.fantur.ws;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import grupo3.fantur.dao.MedioDePagoDao;
import grupo3.fantur.model.MedioDePago;

@Path("/medio-de-pago")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class MedioDePagoResource {

	@Inject
	private MedioDePagoDao medioDePagoDao;

	@GET
	public Response getMediosDePago() {
		List<MedioDePago> mediosDePago = medioDePagoDao.findAll();
		return Response.ok(mediosDePago).build();
	}

	@GET
	@Path("/{medioDePagoId}")
	public Response getMedioDePago(@PathParam("medioDePagoId") long id) {
		MedioDePago medioDePago = medioDePagoDao.findById(id);
		return Response.ok(medioDePago).build();
	}

}
