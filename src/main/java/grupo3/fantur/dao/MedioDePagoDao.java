package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.MedioDePago;

@Local
public interface MedioDePagoDao {

	void create(MedioDePago medioDePago);

	MedioDePago findById(Long id);

	void update(MedioDePago medioDePago);

	void delete(MedioDePago medioDePago);

	List<MedioDePago> findAll();

}
