/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import model.entity.SystemUser;
import model.repo.UserRepo;

/**
 *
 * @author kenanince
 */
@Named(value = "sb")
@SessionScoped
public class SessionBean implements Serializable {

	private static final long serialVersionUID = 8450046583449854177L;

	private SystemUser entity;

	@Inject
	private UserRepo repo;

	public void login() {
		SystemUser user = repo.checkLogin(entity);
		if ( user != null ) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("authenticated", user);
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The user does not exists"));
		}
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

}
