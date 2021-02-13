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
	
	public UserAccount(String pusername, String ppassword, int pindexGender, String pcareer, String pbirthday, String pimagePath, String pbrowser) {
		username = pusername;
		password = ppassword;
		gender = genders[pindexGender].name();
		browser = pbrowser;
		career = pcareer;
		birthday = pbirthday;
		imagePath = pimagePath;
	}
	
	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
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
