package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.PasajeDao;
import grupo3.fantur.model.Pasaje;

@Stateless
public class PasajeDaoImpl implements PasajeDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(Pasaje pasaje) {
		em.persist(pasaje);
	}

	@Override
	public Pasaje findById(Long id) {
		return em.find(Pasaje.class, id);
	}

	@Override
	public void update(Pasaje pasaje) {
		em.merge(pasaje);
	}

	@Override
	public void delete(Pasaje pasaje) {
		em.remove(em.contains(pasaje) ? pasaje : em.merge(pasaje));
	}

	@Override
	public List<Pasaje> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Pasaje> q = cb.createQuery(Pasaje.class);

		Root<Pasaje> root = q.from(Pasaje.class);
		q.select(root);

		TypedQuery<Pasaje> query = em.createQuery(q);

		return query.getResultList();
	}

}
