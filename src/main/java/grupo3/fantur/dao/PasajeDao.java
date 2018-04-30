package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.Pasaje;

@Local
public interface PasajeDao {

	void create(Pasaje pasaje);

	Pasaje findById(Long id);

	void update(Pasaje pasaje);

	void delete(Pasaje pasaje);

	List<Pasaje> findAll();

}
