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
    
    public ClassroomGUI(Classroom croom) {
    	classroom = croom;
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
    
    public boolean checkLogIn() throws IOException {
    	boolean logged = false;
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
        		}
        	}
    	return logged;	
    }
    
    @FXML
    public void loadUserAccountList(ActionEvent event) throws IOException {
    	try {
	    	if(classroom.getUserAccounts() == null) {
	    		Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Log In incorrect");
		    	alert.setHeaderText(null);
		    	alert.setContentText("The username or password given are incorrect");
		    	alert.showAndWait();
	    	}
	    	else {
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
    	} catch(NullPointerException npe) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Log In incorrect");
	    	alert.setHeaderText(null);
	    	alert.setContentText("The username or password given are incorrect");
	    	alert.showAndWait();
    	}
    }
    
    public void loadBrowser(ActionEvent event) throws IOException {
    	FileChooser fileChooser = new FileChooser();

        File selectedFile = fileChooser.showOpenDialog(null);
        if(selectedFile != null) {
        	txtDirectoryImage.setText(selectedFile.getPath());
        }
    }
    
    public void addUserAccount(ActionEvent event) throws IOException {
    	int indexGender = 0;
    	if(txtfieldUsername.getText().isEmpty() || txtfieldPassword.getText().isEmpty() || (rbtMale.isSelected() == false && rbtFemale.isSelected() == false && rbtOther.isSelected() == false) || (checkSoftWare.isSelected() == false && checkTelematic.isSelected() == false && checkIndustrial.isSelected() == false) || dateBirthday.getValue().toString().equals(null)) {
    		Alert alert = new Alert(AlertType.ERROR);
	    	alert.setTitle("Validation Error");
	    	alert.setHeaderText(null);
	    	alert.setContentText("You must fill each field in the form");
	    	alert.showAndWait();
    	}
    	else {
	    	if(rbtFemale.isArmed() == true) {
	    		indexGender = 1;
	    	}
	    	else if(rbtOther.isArmed() == true) {
	    		indexGender = 2;
	    	}
	    	
	    	String careers = "";
	    	if(checkSoftWare.isSelected() == true) {
	    		careers = checkSoftWare.getText()+"/n";
	    	}
	    	if(checkTelematic.isSelected() == true) {
				careers += checkTelematic.getText()+"/n";
			}
	    	if(checkIndustrial.isSelected() == true) {
				careers += checkIndustrial.getText()+"/n";
			}
	    	String birthdayDate = dateBirthday.getValue().toString();
	    	System.out.println(birthdayDate);
	    	String browser = (String)choiceBrowser.getValue();
	    	
	    	//add userAccount in the model
	    	try {
	    		classroom.addUserAcount(txtfieldUsername.getText(),txtfieldPassword.getText(), indexGender, careers, birthdayDate, txtDirectoryImage.getText(), browser);
	    	} catch(NullPointerException npe) {
	    		Alert alert = new Alert(AlertType.ERROR);
		    	alert.setTitle("Account not created");
		    	alert.setHeaderText(null);
		    	alert.setContentText("The new account has not been created");
		    	alert.showAndWait();
	    	}
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
    }
}
