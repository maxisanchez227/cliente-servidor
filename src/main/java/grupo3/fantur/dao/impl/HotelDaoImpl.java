package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.HotelDao;
import grupo3.fantur.model.Hotel;

@Stateless
public class HotelDaoImpl implements HotelDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(Hotel hotel) {
		em.persist(hotel);
	}

	@Override
	public Hotel findById(Long id) {
		return em.find(Hotel.class, id);
	}

	@Override
	public void update(Hotel hotel) {
		em.merge(hotel);
	}

	@Override
	public void delete(Hotel hotel) {
		em.remove(em.contains(hotel) ? hotel : em.merge(hotel));
	}

	@Override
	public List<Hotel> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Hotel> q = cb.createQuery(Hotel.class);

		Root<Hotel> root = q.from(Hotel.class);
		q.select(root);

		TypedQuery<Hotel> query = em.createQuery(q);

		return query.getResultList();
	}

}
