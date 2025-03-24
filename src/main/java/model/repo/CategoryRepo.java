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
import model.entity.Category;

/**
 *
 * @author kenanince
 */
@ApplicationScoped
public class CategoryRepo implements Repo<Category> {

	@PersistenceContext(unitName = "onlinePU")
	private EntityManager em;

	@Override
	@Transactional
	public void create(Category t) {
		this.em.persist(t);
	}
	
	

	@Override
	public Category findByID(Long id) {
		try {
			Query q = em.createQuery("SELECT c FROM Category c where c.id=:id", Category.class);
			q.setParameter("id", id);
			return (Category) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
	
	public Category findByTitle(String title) {
		try {
			Query q = em.createQuery("SELECT c FROM Category c where c.title=:title", Category.class);
			q.setParameter("title", title);
			return (Category) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public List<Category> findAll() {
		Query q = em.createQuery("SELECT c FROM Category c order by c.id desc", Category.class);
		return q.getResultList();
	}

	@Override
	public List<Category> findPaginated(int ic, int cp) {
		int offset = (cp - 1) * ic;
		Query q = em.createQuery("SELECT c FROM Category c limit :itemcount offset :offset order by c.id desc", Category.class);
		q.setParameter("itemcount", ic);
		q.setParameter("offset", offset);
		return q.getResultList();
	}

	@Override
	public Category update(Category t) {
		this.em.persist(t);
		return t;
	}

	@Override
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void delete(Category t) {
		
		this.em.remove(this.em.merge(t));
	}
}
