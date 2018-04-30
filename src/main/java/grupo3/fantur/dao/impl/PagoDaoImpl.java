package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.PagoDao;
import grupo3.fantur.model.Pago;

@Stateless
public class PagoDaoImpl implements PagoDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(Pago pago) {
		em.persist(pago);
	}

	@Override
	public Pago findById(Long id) {
		return em.find(Pago.class, id);
	}

	@Override
	public void update(Pago pago) {
		em.merge(pago);
	}

	@Override
	public void delete(Pago pago) {
		em.remove(em.contains(pago) ? pago : em.merge(pago));
	}

	@Override
	public List<Pago> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pago> q = cb.createQuery(Pago.class);

		Root<Pago> root = q.from(Pago.class);
		q.select(root);

		TypedQuery<Pago> query = em.createQuery(q);

		return query.getResultList();
	}

}
