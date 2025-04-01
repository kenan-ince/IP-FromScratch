/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import java.util.List;
import model.entity.Post;

/**
 *
 * @author kenanince
 */
public class PostRepo implements Repo<Post>{
	
	@PersistenceContext(unitName = "onlinePU")
	private EntityManager em;

	@Override
	public void create(Post t) {
		this.em.persist(t);
	}

	@Override
	public List<Post> findAll() {
		Query q = em.createQuery("SELECT c FROM Post c order by c.id desc", Post.class);
		return q.getResultList();
	}

	@Override
	public List<Post> findPaginated(int ic, int cp) {
		int offset = (cp-1)*ic;
		Query q = em.createQuery("SELECT c FROM Post c limit :itemcount offset :offset order by c.id desc", Post.class);
		q.setParameter("itemcount", ic);
		q.setParameter("offset", offset);
		return q.getResultList();
	}

	@Override
	public Post update(Post t) {
		this.em.persist(t);
		return t;
	}

	@Override
	public void delete(Post t) {
		this.em.remove(t);
	}

	@Override
	public Post findByID(Long id) {
		throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
	}
}
