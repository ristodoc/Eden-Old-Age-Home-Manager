package eden.oldagehome.model;

public class User {
	private String id;
	private String name;
	private String password;
	private String role;

	public User(String id, String password, String name){
		this.id = id;
		this.password=password;
		this.name = name;
	}
	
	public boolean credentialCheck(String password){
		return this.password.equals(password);
	}
}
