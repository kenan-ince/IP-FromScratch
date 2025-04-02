package model.repo;

import java.util.List;
import java.util.Map;

/**
 *
 * @author kenanince
 */
public interface Repo<T> {
	public T create(T t);
	public List<T> findAll();
	public List<T> findPaginated(int ic, int cp);
	public T update(T t);
	public void delete(T t);
	public List<T> findByField(String f, Object s);
	public T findByFieldAND(Map<String, Object> map);
	public T findByFieldOR(Map<String, Object> map);
}
