package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.ActividadDao;
import grupo3.fantur.model.Actividad;

@Stateless
public class ActividadDaoImpl implements ActividadDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(Actividad actividad) {
		em.persist(actividad);
	}

	@Override
	public Actividad findById(Long id) {
		return em.find(Actividad.class, id);
	}

	@Override
	public void update(Actividad actividad) {
		em.merge(actividad);
	}

	@Override
	public void delete(Actividad actividad) {
		em.remove(em.contains(actividad) ? actividad : em.merge(actividad));
	}

	@Override
	public List<Actividad> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Actividad> q = cb.createQuery(Actividad.class);

		Root<Actividad> root = q.from(Actividad.class);
		q.select(root);

		TypedQuery<Actividad> query = em.createQuery(q);

		return query.getResultList();
	}

}
