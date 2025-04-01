package model.repo;

import jakarta.enterprise.context.ApplicationScoped;
import model.entity.SystemUser;

/**
 *
 * @author kenanince
 */
@ApplicationScoped
public class UserRepo extends AbstractRepo<SystemUser>{

	public UserRepo() {
		super(SystemUser.class);
	}
}
