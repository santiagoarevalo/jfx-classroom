package model;

import java.util.ArrayList;

public class Classroom {
	
	private ArrayList<UserAccount> userAccounts;
	
	public Classroom() {
		userAccounts = new ArrayList<UserAccount>();
	}
	
	public void addUserAcount(String username, String password, int indexGender, String careers, String birthday, String imagePath, String browser) {
		UserAccount newUser = new UserAccount(username, password, indexGender, careers, birthday, imagePath, browser);
		userAccounts.add(newUser);
	}
	
	public ArrayList<UserAccount> getUserAccounts() {
		return userAccounts;
	}
}
