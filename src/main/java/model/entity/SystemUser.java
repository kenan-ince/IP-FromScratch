package model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.validation.constraints.Email;

/**
 *
 * @author kenanince
 */
@Entity
public class SystemUser extends BaseEntity{

	private static final long serialVersionUID = 6470495999533852640L;
	private String nameSurname;

	@Email
	@Column(unique = true)
	private String email;

	private String password;

	public SystemUser() {
	}

	public SystemUser(Long id, String nameSurname, String email, String password) {
		super(id);
		this.nameSurname = nameSurname;
		this.email = email;
		this.password = password;
	}

	public String getNameSurname() {
		return nameSurname;
	}

	public void setNameSurname(String nameSurname) {
		this.nameSurname = nameSurname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "SystemUser{" + "id=" + this.getId() + ", nameSurname=" + nameSurname + ", email=" + email + ", password=" + password + '}';
	}
}
