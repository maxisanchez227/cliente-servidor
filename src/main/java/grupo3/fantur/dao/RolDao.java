package grupo3.fantur.dao;

import java.util.List;

import javax.ejb.Local;

import grupo3.fantur.model.Rol;

@Local
public interface RolDao {

	void create(Rol rol);

	Rol findById(Long id);

	void update(Rol rol);

	void delete(Rol rol);

	List<Rol> findAll();

}
