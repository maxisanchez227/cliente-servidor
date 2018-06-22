package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.ActividadesPorPaquete;

@Local
public interface ActividadesPorPaqueteDao {

	void create(ActividadesPorPaquete actividadesPorPaquete);

	ActividadesPorPaquete findById(Long id);

	void update(ActividadesPorPaquete actividadesPorPaquete);

	void delete(ActividadesPorPaquete actividadesPorPaquete);

	List<ActividadesPorPaquete> findAll();

}
