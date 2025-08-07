package eden.oldagehome.model;

public abstract class User {
	private String id;
	private String name;
	private String password;
	private String role;

	protected User(String id, String password, String name){
		this.id = id;
		this.password=password;
		this.name = name;
	}
	
	public boolean credentialCheck(String password){
		return this.password.equals(password);
	}
}
