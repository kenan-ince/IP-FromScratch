/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import model.entity.SystemUser;

/**
 *
 * @author kenanince
 */
@ApplicationScoped
public class UserRepo implements Repo<SystemUser>{
	
	@PersistenceContext(unitName = "onlinePU")
	private EntityManager em;

	@Override
	@Transactional
	public void create(SystemUser t) {
		this.em.persist(t);
	}
	
	public SystemUser findByEmail(String title) {
		try {
			Query q = em.createQuery("SELECT c FROM SystemUser c where c.email=:email", SystemUser.class);
			q.setParameter("email", title);
			return (SystemUser) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public SystemUser checkLogin(SystemUser user) {
		try {
			Query q = em.createQuery("SELECT c FROM SystemUser c where c.email=:email and c.password=:pass", SystemUser.class);
			q.setParameter("email", user.getEmail());
			q.setParameter("pass", user.getPassword());
			return (SystemUser) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<SystemUser> findAll() {
		Query q = em.createQuery("SELECT c FROM SystemUser c order by c.id desc", SystemUser.class);
		return q.getResultList();
	}

	@Override
	public List<SystemUser> findPaginated(int ic, int cp) {
		int offset = (cp-1)*ic;
		Query q = em.createQuery("SELECT c FROM SystemUser c limit :itemcount offset :offset order by c.id desc", SystemUser.class);
		q.setParameter("itemcount", ic);
		q.setParameter("offset", offset);
		return q.getResultList();
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRED)
	public SystemUser update(SystemUser t) {
		this.em.persist(this.em.merge(t));
		return t;
	}

	@Override
	@Transactional
	public void delete(SystemUser t) {
		this.em.remove(this.em.merge(t));
	}

	@Override
	public SystemUser findByID(Long id) {
		try {
			Query q = em.createQuery("SELECT c FROM SystemUser c where c.id=:id", SystemUser.class);
			q.setParameter("id", id);
			return (SystemUser) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
