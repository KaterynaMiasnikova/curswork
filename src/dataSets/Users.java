package dataSets;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="users")
public class Users implements Serializable {
	private static final long serialVersionUID = -8706689714326132798L;

	@Id
	@Column(name = "id_u")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "name_u")
	private String name;

	@Column(name = "surname")
	private String surname;

	@Column(name = "email")
	private String email;

	@Column(name = "password_u")
	private String password_u;

	public Users() {
		Integer test = 0;
		++test;
	}

	public Users(String name, String surname, String email, String pass) {
		this.setId(-1);
		this.setName(name);
		this.setSurname(surname);
		this.setEmail(email);
		this.setPassword_u(pass);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword_u() {
		return password_u;
	}

	public void setPassword_u(String pass) {
		this.password_u = pass;
	}
}