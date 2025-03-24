/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import model.entity.Category;
import model.repo.CategoryRepo;

/**
 *
 * @author kenanince
 */
@Named(value = "cb")
@ViewScoped
public class CategoryBean implements Serializable {

	private static final long serialVersionUID = -3614181072426519405L;

	private Category entity;

	@Inject
	private CategoryRepo repo;

	public CategoryBean() {
	}

	public void create() {
		Category tmp = repo.findByTitle(this.entity.getTitle());
		if (tmp == null) {
			repo.create(entity);
			this.entity = new Category();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The category with title: "+this.entity.getTitle()+" already exists!"));
		}
	}
	
	public void clearForm() {
		this.entity = new Category();
	}

	public void update() {
		repo.update(entity);
	}

	public void delete(Category c) {
		repo.delete(c);
	}

	public Category getEntity() {
		if (this.entity == null) {
			this.entity = new Category();
		}
		return entity;
	}

	public void setEntity(Category entity) {
		this.entity = entity;
	}

	public List<Category> getList() {
		return repo.findAll();
	}
}
