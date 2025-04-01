package controller;

import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import java.util.HashMap;
import model.entity.SystemUser;

/**
 *
 * @author kenanince
 */
@Named(value = "sb")
@SessionScoped
public class SessionBean extends AbstractBean<SystemUser> {

	private static final long serialVersionUID = 8450046583449854177L;

	public SessionBean() {
		super(SystemUser.class);
	}

	public String login() {
		
		HashMap<String, Object> criteria = new HashMap<>();
		criteria.put("email", this.getEntity().getEmail());
		criteria.put("password", this.getEntity().getPassword());
		
		SystemUser user = repo.findByFields(criteria);
		if (user != null) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("authenticated", user);
			return "/back/landing?faces-redirect=true";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("The user does not exists"));
		}
		return "/login?faces-redirect=true";
	}
}
