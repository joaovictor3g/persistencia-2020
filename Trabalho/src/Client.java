
public class Client {
	private int id = 0;
	private String name;
	private String cpf;
	private String address;
	private String email;
	private String city;
	private String phone;
	private String uf;
	private String cep;
	
	public Client() {
		
	}
	
	public Client(int id, String name, String cpf, String address, String email, String city, String phone, String uf,
			String cep) {
		super();
		this.id = id;
		this.name = name;
		this.cpf = cpf;
		this.address = address;
		this.email = email;
		this.city = city;
		this.phone = phone;
		this.uf = uf;
		this.cep = cep;
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
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getCep() {
		return cep;
	}
	public void setCep(String cep) {
		this.cep = cep;
	}
	
	@Override
	public String toString() {
		return id + ";" + name + ";" + cpf + ";" + address + ";" + email + ";" + city + ";" + phone + ";" + uf + ";" + cep;
	}
	
	
}
