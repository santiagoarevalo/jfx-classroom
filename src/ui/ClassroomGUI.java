package ui;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import model.Classroom;

public class ClassroomGUI {
	@FXML
    private Pane mainPanel;

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
    private ComboBox<?> comboBrowser;

    @FXML
    private Button bttSignIn;

    @FXML
    private Button bttCreateAccount;
    
    private Classroom classroom;
    
    public ClassroomGUI(Classroom croom) {
    	classroom = croom;
    }
}
