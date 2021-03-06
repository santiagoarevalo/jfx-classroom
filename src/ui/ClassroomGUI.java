package ui;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import model.Classroom;
import model.UserAccount;

public class ClassroomGUI {
	@FXML
    private Pane mainPanel;
	
	@FXML
    private Pane registerPane;
	
	@FXML
    private Pane loginPane;
	
	@FXML
    private Pane listPane;

    @FXML
    private TextField txtUsername;

    @FXML
    private Button btSignUp;

    @FXML
    private Button btLogIn;

    @FXML
    private PasswordField pwPassword;
    @FXML
    private TextField txtfieldUsername;

    @FXML
    private TextField txtfieldPassword;

    @FXML
    private TextField txtDirectoryImage;

    @FXML
    private RadioButton rbtMale;

    @FXML
    private RadioButton rbtFemale;

    @FXML
    private RadioButton rbtOther;

    @FXML
    private CheckBox checkSoftWare;

    @FXML
    private CheckBox checkTelematic;

    @FXML
    private CheckBox checkIndustrial;

    @FXML
    private DatePicker dateBirthday;

    @FXML
    private ChoiceBox<String> choiceBrowser;

    @FXML
    private Button bttSignIn;

    @FXML
    private Button bttCreateAccount;
    
    @FXML
    private Label lbUsername;

    @FXML
    private Button bttLogOut;

    @FXML
    private TableView<UserAccount> tvUserAccountList;
    
    @FXML
    private TableColumn<UserAccount, String> tcUsername;

    @FXML
    private TableColumn<UserAccount, String> tcGender;

    @FXML
    private TableColumn<UserAccount, String> tcCareer;

    @FXML
    private TableColumn<UserAccount, String> tcBirthday;

    @FXML
    private TableColumn<UserAccount, String> tcBrowser;

    @FXML
    private ImageView imageProfile;
    
    private Classroom classroom;
    
    public ClassroomGUI(Classroom classroom) {
    	this.classroom = classroom;
    }
    
    public void initialize() {
    	//the method (initialize) is called several times by diferents fxml file loads 
    }
    
    private void initializeTableView() {
    	ObservableList<UserAccount> observableList;
    	observableList = FXCollections.observableArrayList(classroom.getUserAccounts());
    	
		tvUserAccountList.setItems(observableList);
		tcUsername.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("username")); //the tableview search for a method called getUsername
		tcGender.setCellValueFactory(new PropertyValueFactory<UserAccount,String>("gender")); //the tableview search for a method called getGender
		tcCareer.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("career")); //the tableview search for a method called getCareer
		tcBirthday.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("birthday")); //the tableview search for a method called getBirthday
		tcBrowser.setCellValueFactory(new PropertyValueFactory<UserAccount, String>("browser")); //the tableview search for a method called getBrowser
    }
    
    @FXML
    public void loadAddUserAccount(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("register.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent addUserAccountPane = fxmlLoader.load();
    	
		loginPane.getChildren().clear();
		loginPane.getChildren().setAll(addUserAccountPane);
		
		choiceBrowser.setValue("Chrome");
		choiceBrowser.getItems().addAll("Opera", "Internet Explorer", "Chrome", "Edge", "Safari", "Firefox", "Brave");
    }
    
    @FXML
    public void loadLogIn(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent login = fxmlLoader.load();
    	
		registerPane.getChildren().clear();
		registerPane.getChildren().setAll(login);
    }
    
    @FXML
    public void LogOut(ActionEvent event) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
		
		fxmlLoader.setController(this);    	
		Parent login = fxmlLoader.load();
    	
		listPane.getChildren().clear();
		listPane.getChildren().setAll(login);
    }
    
    public boolean checkLogIn() throws IOException {
    	boolean logged = false;
    	try {
	    	if(classroom.getUserAccounts().size() == 0) {
	    		Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Log In incorrect");
		    	alert.setHeaderText(null);
		    	alert.setContentText("The username or password given are incorrect");
		    	alert.showAndWait();
		    	txtUsername.setText(""); 
    	    	pwPassword.setText("");
	    	}
	    	else {
	    		for(int i=0; i<classroom.getUserAccounts().size(); i++) {
	        		if(txtUsername.getText().equals(classroom.getUserAccounts().get(i).getUsername()) && pwPassword.getText().equals(classroom.getUserAccounts().get(i).getPassword())) {
	        			logged = true;
	        		}
	        		else {
	        			Alert alert = new Alert(AlertType.ERROR);
	        	    	alert.setTitle("Log In incorrect");
	        	    	alert.setHeaderText(null);
	        	    	alert.setContentText("The username or password given are incorrect");
	        	    	alert.showAndWait();
	        	    	txtUsername.setText(""); 
	        	    	pwPassword.setText("");
	        		}
	        	}
	    	}
    	} catch(NullPointerException npe) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Log In incorrect");
	    	alert.setHeaderText(null);
	    	alert.setContentText("The username or password given are incorrect");
	    	alert.showAndWait();
    	}
    	return logged;	
    }
    
    @FXML
    public void loadUserAccountList(ActionEvent event) throws IOException {
    	if(checkLogIn() == true) {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("account-list.fxml"));
			
			fxmlLoader.setController(this);
			Parent UserAccountListPane = fxmlLoader.load();
	    	
			loginPane.getChildren().clear();
			loginPane.getChildren().addAll(UserAccountListPane);
			
			lbUsername.setText(txtUsername.getText());
			
			InputStream stream = new FileInputStream(txtDirectoryImage.getText());
			Image image = new Image(stream);
			imageProfile.setImage(image);
	    	initializeTableView();
		}
    }
    
    public void loadBrowser(ActionEvent event) throws IOException {
    	FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null) {
        	txtDirectoryImage.setText(selectedFile.getPath());
        }
    }
    
    public void showLogin() throws IOException {
		FXMLLoader fxmlloader = new FXMLLoader(getClass().getResource("login.fxml"));
		fxmlloader.setController(this);
		Parent root = fxmlloader.load();
		mainPanel.getChildren().clear();
		mainPanel.getChildren().setAll(root);
	}
    
    public void addUserAccount(ActionEvent event) throws IOException {
    	if(txtfieldUsername.getText().isEmpty() || txtfieldPassword.getText().isEmpty() || (rbtMale.isSelected() == false && rbtFemale.isSelected() == false && rbtOther.isSelected() == false) || (checkSoftWare.isSelected() == false && checkTelematic.isSelected() == false && checkIndustrial.isSelected() == false) || dateBirthday.getValue().toString().equals(null)) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Validation Error");
	    	alert.setHeaderText(null);
	    	alert.setContentText("You must fill each field in the form");
	    	alert.showAndWait();
    	}
    	else {
    		String username = txtfieldUsername.getText();
    		String password = txtfieldPassword.getText();
    		
    		int indexGender = 0;
	    	if(rbtFemale.isArmed() == true) {
	    		indexGender = 1;
	    	}
	    	else if(rbtOther.isArmed() == true) {
	    		indexGender = 2;
	    	}
	    	
	    	String careers = "";
	    	if(checkSoftWare.isSelected() == true) {
	    		careers = checkSoftWare.getText()+"-";
	    	}
	    	if(checkTelematic.isSelected() == true) {
				careers += checkTelematic.getText()+"-";
			}
	    	if(checkIndustrial.isSelected() == true) {
				careers += checkIndustrial.getText();
			}
	    	String birthdayDate = dateBirthday.getValue().toString();
	    	String imagePath = txtDirectoryImage.getText();
	    	String browser = (String)choiceBrowser.getValue();
	   
	    	if(classroom != null && classroom.addUserAcount(username, password, indexGender, careers, birthdayDate, imagePath, browser)) {
	    		//clean the fields in the GUI
		    	txtfieldUsername.setText("");
		    	txtfieldPassword.setText("");
		    	
		    	//show the success message
		    	Alert alert = new Alert(AlertType.INFORMATION);
		    	alert.setTitle("Account created");
		    	alert.setHeaderText(null);
		    	alert.setContentText("The new account has been created succesfully");
		
		    	alert.showAndWait();
	    	}
	    	else {
	    		Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Sign Up incorrect");
		    	alert.setHeaderText(null);
		    	alert.setContentText("The user account can not be created, NullPointerException");
		    	alert.showAndWait();
	    	}
    	}
    }
    
    @FXML
    public void exportUserAccounts(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Save Resource File");
    	File file = fileChooser.showSaveDialog(listPane.getScene().getWindow());
    	if(file != null) {
    		try {
    			classroom.exportUserAccounts(file.getAbsolutePath());
    			Alert alert = new Alert(AlertType.INFORMATION);
    		    alert.setTitle("Export User Accounts");
    		    alert.setHeaderText(null);
    		    alert.setContentText("The user accounts have been exported");
    		    alert.showAndWait();
    		} catch(IOException io) {
    			Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Export User Accounts");
    		    alert.setHeaderText(null);
    		    alert.setContentText("The user accounts have not been exported");
    		    alert.showAndWait();
    		}
    	}
    }

    @FXML
    public void importUserAccounts(ActionEvent event) {
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Open Resource File");
    	File file = fileChooser.showOpenDialog(listPane.getScene().getWindow());
    	if(file != null) {
    		try {
    			classroom.importUserAccounts(file.getAbsolutePath());
    			Alert alert = new Alert(AlertType.INFORMATION);
    		    alert.setTitle("Import User Accounts");
    		    alert.setHeaderText(null);
    		    alert.setContentText("The user accounts have been imported");
    		    alert.showAndWait();
    		    initializeTableView();
    		} catch(IOException io) {
    			Alert alert = new Alert(AlertType.ERROR);
    		    alert.setTitle("Import User Accounts");
    		    alert.setHeaderText(null);
    		    alert.setContentText("The user accounts have not been imported");
    		    alert.showAndWait();
    		}
    	}
    }
}
