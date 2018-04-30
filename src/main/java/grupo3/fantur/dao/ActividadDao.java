package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.Actividad;

@Local
public interface ActividadDao {

	void create(Actividad actividad);

	Actividad findById(Long id);

	void update(Actividad actividad);

	void delete(Actividad actividad);

	List<Actividad> findAll();

}
