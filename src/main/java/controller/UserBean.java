package controller;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;
import model.entity.SystemUser;

/**
 *
 * @author kenanince
 */
@Named(value = "ub")
@ViewScoped
public class UserBean  extends AbstractBean<SystemUser> {

	private static final long serialVersionUID = -3614181072426519405L;

	public UserBean() {
		super(SystemUser.class);
	}
}
