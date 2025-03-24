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
import model.entity.SystemUser;
import model.repo.UserRepo;

/**
 *
 * @author kenanince
 */
@Named(value = "ub")
@ViewScoped
public class UserBean implements Serializable {

	private static final long serialVersionUID = -3614181072426519405L;

	private SystemUser entity;

	@Inject
	private UserRepo repo;

	public UserBean() {
	}

	public void create() {
		SystemUser tmp = repo.findByEmail(this.entity.getEmail());
		if (tmp == null) {
			repo.create(entity);
			this.entity = new SystemUser();
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The user with email: "+this.entity.getEmail()+" already exists!"));
		}
	}
	
	public void clearForm() {
		this.entity = new SystemUser();
	}

	public void update() {
		repo.update(entity);
	}

	public void delete() {
		repo.delete(this.entity);
		this.entity = new SystemUser();
	}

	public SystemUser getEntity() {
		if (this.entity == null) {
			this.entity = new SystemUser();
		}
		return entity;
	}

	public void setEntity(SystemUser entity) {
		this.entity = entity;
	}

	public List<SystemUser> getList() {
		return repo.findAll();
	}
}
