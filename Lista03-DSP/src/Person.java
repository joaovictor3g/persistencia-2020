import java.io.Serializable;

public class Person implements Serializable {
	private int id;
	private String name;
	private String email;
	private String phone;
	
	public Person(int _id, String _name, String _email, String _phone) {
		this.id = _id;
		this.name = _name;
		this.email = _email;
		this.phone = _phone;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", email=" + email + ", phone=" + phone + "]" + "\n";
	}
	
	
}
