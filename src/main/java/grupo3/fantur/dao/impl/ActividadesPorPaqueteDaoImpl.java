package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.ActividadesPorPaqueteDao;
import grupo3.fantur.model.ActividadesPorPaquete;

@Stateless
public class ActividadesPorPaqueteDaoImpl implements ActividadesPorPaqueteDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(ActividadesPorPaquete actividadesPorPaquete) {
		em.persist(actividadesPorPaquete);
	}

	@Override
	public ActividadesPorPaquete findById(Long id) {
		return em.find(ActividadesPorPaquete.class, id);
	}

	@Override
	public void update(ActividadesPorPaquete actividadesPorPaquete) {
		em.merge(actividadesPorPaquete);
	}

	@Override
	public void delete(ActividadesPorPaquete actividadesPorPaquete) {
		em.remove(em.contains(actividadesPorPaquete) ? actividadesPorPaquete : em.merge(actividadesPorPaquete));
	}

	@Override
	public List<ActividadesPorPaquete> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<ActividadesPorPaquete> q = cb.createQuery(ActividadesPorPaquete.class);

		Root<ActividadesPorPaquete> root = q.from(ActividadesPorPaquete.class);
		q.select(root);

		TypedQuery<ActividadesPorPaquete> query = em.createQuery(q);

		return query.getResultList();
	}

}
