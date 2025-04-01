package model.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kenanince
 * @param <T> entity class
 */
public abstract class AbstractRepo<T> implements Repo<T> {

	private final Class<T> entityClass;

	@PersistenceContext(unitName = "onlinePU")
	protected EntityManager em;

	public AbstractRepo(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	@Override
	public T create(T t) {
		this.em.persist(t);
		this.em.flush();
		return t;
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	@Override
	public T update(T t) {
		this.em.merge(t);
		return t;
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	@Override
	public void delete(T t) {
		this.em.remove(this.em.merge(t));
	}

	public T find(Object id) {
		return this.em.find(entityClass, id);
	}

	@Override
	public List<T> findAll() {
		Query q = em.createQuery("SELECT t FROM " + entityClass.getName() + " t order by t.id desc", entityClass);
		return q.getResultList();
	}

	@Override
	public List<T> findPaginated(int ic, int cp) {
		int offset = (cp - 1) * ic;
		Query q = em.createQuery("SELECT t FROM " + entityClass.getName() + " t order by t.id desc", entityClass);
		q.setFirstResult(offset);
		q.setMaxResults(ic);
		return q.getResultList();
	}

	@Override
	public List<T> findByField(String field, Object search) {
		try {
			Query q = em.createQuery("SELECT t FROM  " + entityClass.getName() + "  t where t." + field + "=:search", entityClass);
			q.setParameter("search", search);
			return q.getResultList();
		} catch (Exception e) {
			System.out.println("---------------------------------------------------------------");
			System.out.println("Field: " + field);
			System.out.println("Search: " + search);
			System.out.println(e.getMessage());
			System.out.println("---------------------------------------------------------------");
			return null;
		}
	}

	@Override
	public T findByFields(Map<String, Object> searchItems) {
		String condition = "";

		for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			if (condition.length() > 1) {
				condition += " AND t." + key + " = :" + key;
			} else {
				condition += " t." + key + " = :" + key;
			}
		}

		try {
			Query q = em.createQuery("SELECT t FROM  " + entityClass.getName() + "  t where " + condition, entityClass);

			for (Map.Entry<String, Object> entry : searchItems.entrySet()) {
				String key = entry.getKey();
				Object value = entry.getValue();
				condition += " t." + key + " = :" + key;
				q.setParameter(key, value);
			}

			return (T) q.getSingleResult();
		} catch (Exception e) {
			return null;
		}
	}
}
