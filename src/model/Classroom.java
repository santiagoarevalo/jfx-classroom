package model;

import java.util.ArrayList;

public class Classroom {
	
	private ArrayList<UserAccount> userAccounts;
	
	public Classroom() {
		userAccounts = new ArrayList<UserAccount>();
	}
	
	public boolean addUserAcount(String username, String password, int indexGender, String careers, String birthday, String imagePath, String browser) {
		boolean added = false;
		added = userAccounts.add(new UserAccount(username, password, indexGender, careers, birthday, imagePath, browser));
		return added;
	}
	
	public ArrayList<UserAccount> getUserAccounts() {
		return userAccounts;
	}
}
