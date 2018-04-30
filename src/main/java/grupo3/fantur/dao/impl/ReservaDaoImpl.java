package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.ReservaDao;
import grupo3.fantur.model.Reserva;

@Stateless
public class ReservaDaoImpl implements ReservaDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(Reserva reserva) {
		em.persist(reserva);
	}

	@Override
	public Reserva findById(Long id) {
		return em.find(Reserva.class, id);
	}

	@Override
	public void update(Reserva reserva) {
		em.merge(reserva);
	}

	@Override
	public void delete(Reserva reserva) {
		em.remove(em.contains(reserva) ? reserva : em.merge(reserva));
	}

	@Override
	public List<Reserva> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Reserva> q = cb.createQuery(Reserva.class);

		Root<Reserva> root = q.from(Reserva.class);
		q.select(root);

		TypedQuery<Reserva> query = em.createQuery(q);

		return query.getResultList();
	}

}
