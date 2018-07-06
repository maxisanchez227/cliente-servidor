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

import grupo3.fantur.dao.HotelDao;
import grupo3.fantur.model.Hotel;

@Path("/hotel")
@Consumes(MediaType.APPLICATION_JSON)
@Produces({ MediaType.APPLICATION_JSON })
public class HotelResource {

	@Inject
	private HotelDao hotelDao;

	@GET
	public Response getHoteles() {
		List<Hotel> hoteles = hotelDao.findAll();
		return Response.ok(hoteles).build();
	}

	@GET
	@Path("/{hotelId}")
	public Response getHotel(@PathParam("hotelId") long id) {
		Hotel hotel = hotelDao.findById(id);
		return Response.ok(hotel).build();
	}
	
	@POST
	public void addRol(Hotel hotel) {
		hotelDao.create(hotel);
	}

	@PUT
	public void updateRol(Hotel hotel) {
		hotelDao.update(hotel);
	}

	@DELETE
	@Path("/{hotelId}")
	public void deleteHotel(@PathParam("hotelId") long id) {
		hotelDao.delete(hotelDao.findById(id));
	}

}
