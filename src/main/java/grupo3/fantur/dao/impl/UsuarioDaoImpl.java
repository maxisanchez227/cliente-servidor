package grupo3.fantur.dao.impl;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import grupo3.fantur.dao.UsuarioDao;
import grupo3.fantur.model.Usuario;

@Stateless
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext(unitName = "FanturPU")
	EntityManager em;

	@Override
	public void create(Usuario usuario) {
		em.persist(usuario);
	}

	@Override
	public Usuario findById(Long id) {
		return em.find(Usuario.class, id);
	}

	@Override
	public void update(Usuario usuario) {
		em.merge(usuario);
	}

	@Override
	public void delete(Usuario usuario) {
		em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
	}

	@Override
	public List<Usuario> findAll() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> q = cb.createQuery(Usuario.class);

		Root<Usuario> root = q.from(Usuario.class);
		q.select(root);

		TypedQuery<Usuario> query = em.createQuery(q);

		return query.getResultList();
	}
	
	/*
	 * Recupera usuario a partir de usuario y contrasenia ingresados
	 * 
	 */
	@Override
	public Usuario iniciarSesion(Usuario usuario) {
		Usuario u = null;
		String sql;
		try {
			sql = "FROM Usuario u WHERE u.username=?1 AND u.password=?2";
			Query query = em.createQuery(sql);
			query.setParameter(1, usuario.getUsername());
			query.setParameter(2, usuario.getPassword());
			List<Usuario> usuariosRegistrados = query.getResultList();
			if(!usuariosRegistrados.isEmpty()) {
				u = usuariosRegistrados.get(0);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return u;
	}

}
