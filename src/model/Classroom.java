package model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	public void importUserAccounts(String path) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(path));
	    String line = br.readLine();
	    while(line!=null){
	      String[] parts = line.split(";");
	      int indexGender = Integer.parseInt(parts[2]);
	      addUserAcount(parts[0],parts[1], indexGender,parts[3],parts[4],parts[5],parts[6]);
	      line = br.readLine();
	    }
	    br.close();
	}
	
	public void exportUserAccounts(String fileName) throws FileNotFoundException{
	    PrintWriter pw = new PrintWriter(fileName);

	    for(int i=0;i<userAccounts.size();i++){
	      UserAccount myAccount = userAccounts.get(i);
	      pw.println(myAccount.getUsername()+";"+myAccount.getPassword()+";"+myAccount.getGender()+";"+myAccount.getCareer()+";"+myAccount.getBirthday()+";"+myAccount.getImagePath()+";"+myAccount.getBrowser());
	    }

	    pw.close();
	  }
}
