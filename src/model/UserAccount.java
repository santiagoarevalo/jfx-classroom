package model;

public class UserAccount {
	private final Gender[] genders = Gender.values();
	private String username;
	private String password;
	private String gender;
	private String career;
	private String birthday;
	private String imagePath;
	private String browser;
	
	public UserAccount(String username, String password, int indexGender, String career, String birthday, String imagePath, String browser) {
		this.username = username;
		this.password = password;
		gender = genders[indexGender].toString();
		this.browser = browser;
		this.career = career;
		this.birthday = birthday;
		this.imagePath = imagePath;
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

	public String getImagePath() {
		return imagePath;
	}

	public String getBrowser() {
		return browser;
	}

	public String getCareer() {
		return career;
	}

	public String getBirthday() {
		return birthday;
	}

}
