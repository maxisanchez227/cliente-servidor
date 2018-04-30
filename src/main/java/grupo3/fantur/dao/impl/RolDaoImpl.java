package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.RolDao;
import grupo3.fantur.model.Rol;

@Stateless
public class RolDaoImpl implements RolDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(Rol rol) {
		em.persist(rol);
	}

	@Override
	public Rol findById(Long id) {
		return em.find(Rol.class, id);
	}

	@Override
	public void update(Rol rol) {
		em.merge(rol);
	}

	@Override
	public void delete(Rol rol) {
		/*
		 * check if the entity is managed by the EntityManager.contains() and if not,
		 * then make it managed by the EntityManager.merge()
		 */
		em.remove(em.contains(rol) ? rol : em.merge(rol));
	}

	@Override
	public List<Rol> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Rol> q = cb.createQuery(Rol.class);

		Root<Rol> root = q.from(Rol.class);
		q.select(root);

		TypedQuery<Rol> query = em.createQuery(q);

		return query.getResultList();
	}

}
