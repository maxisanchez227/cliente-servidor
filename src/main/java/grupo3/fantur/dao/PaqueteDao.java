package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.Paquete;

@Local
public interface PaqueteDao {

	void create(Paquete paquete);

	Paquete findById(Long id);

	void update(Paquete paquete);

	void delete(Paquete paquete);

	List<Paquete> findAll();

}
