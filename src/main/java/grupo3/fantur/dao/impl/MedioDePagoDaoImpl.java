package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.MedioDePagoDao;
import grupo3.fantur.model.MedioDePago;

@Stateless
public class MedioDePagoDaoImpl implements MedioDePagoDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(MedioDePago medioDePago) {
		em.persist(medioDePago);
	}

	@Override
	public MedioDePago findById(Long id) {
		return em.find(MedioDePago.class, id);
	}

	@Override
	public void update(MedioDePago medioDePago) {
		em.merge(medioDePago);
	}

	@Override
	public void delete(MedioDePago medioDePago) {
		em.remove(em.contains(medioDePago) ? medioDePago : em.merge(medioDePago));
	}

	@Override
	public List<MedioDePago> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MedioDePago> q = cb.createQuery(MedioDePago.class);

		Root<MedioDePago> root = q.from(MedioDePago.class);
		q.select(root);

		TypedQuery<MedioDePago> query = em.createQuery(q);

		return query.getResultList();
	}

}
