package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.Reserva;

@Local
public interface ReservaDao {

	void create(Reserva reserva);

	Reserva findById(Long id);

	void update(Reserva reserva);

	void delete(Reserva reserva);

	List<Reserva> findAll();

}
