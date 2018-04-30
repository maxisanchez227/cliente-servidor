package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.Pago;

@Local
public interface PagoDao {

	void create(Pago pago);

	Pago findById(Long id);

	void update(Pago pago);

	void delete(Pago pago);

	List<Pago> findAll();

}
