package model;

public class UserAccount {
	private final Career[] careers = Career.values();
	private final Gender[] genders = Gender.values();
	private String username;
	private String password;
	private String gender;
	private String career;
	
	public UserAccount(String username, String password, int indexGender, int indexCareer) {
		this.username = username;
		this.password = password;
		gender = genders[indexGender].name();
		career = careers[indexCareer].name();
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public String getCareer() {
		return career;
	}

}
