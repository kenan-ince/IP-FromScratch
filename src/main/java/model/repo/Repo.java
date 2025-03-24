/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.repo;

import java.util.List;

/**
 *
 * @author kenanince
 */
public interface Repo<T> {
	public T findByID(Long id);
	public void create(T t);
	public List<T> findAll();
	public List<T> findPaginated(int ic, int cp);
	public T update(T t);
	public void delete(T t);
}
