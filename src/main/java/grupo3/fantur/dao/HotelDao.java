package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.Hotel;

@Local
public interface HotelDao {

	void create(Hotel hotel);

	Hotel findById(Long id);

	void update(Hotel hotel);

	void delete(Hotel hotel);

	List<Hotel> findAll();

}
