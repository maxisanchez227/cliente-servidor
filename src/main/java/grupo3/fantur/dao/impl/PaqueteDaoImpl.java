package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.PaqueteDao;
import grupo3.fantur.model.Paquete;

@Stateless
public class PaqueteDaoImpl implements PaqueteDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(Paquete paquete) {
		em.persist(paquete);
	}

	@Override
	public Paquete findById(Long id) {
		return em.find(Paquete.class, id);
	}

	@Override
	public void update(Paquete paquete) {
		em.merge(paquete);
	}

	@Override
	public void delete(Paquete paquete) {
		em.remove(em.contains(paquete) ? paquete : em.merge(paquete));
	}

	@Override
	public List<Paquete> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Paquete> q = cb.createQuery(Paquete.class);

		Root<Paquete> root = q.from(Paquete.class);
		q.select(root);

		TypedQuery<Paquete> query = em.createQuery(q);

		return query.getResultList();
	}

}
