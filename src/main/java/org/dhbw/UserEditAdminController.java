package org.dhbw;

import backend.usability.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserEditAdminController extends Controller implements Initializable {

    @FXML
    private TextField surnameTf;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField usernameTf;

    @FXML
    private ChoiceBox<String> authorizationChoiceBox;
    @FXML
    private Button submitBtn;
    @FXML
    private Button cancelBtn;
    private User user;

    /**
     * Button Action for submit Button.
     */
    @FXML
    public void submit() {
        String surName = surnameTf.getText();
        String name = nameTf.getText();
        String userName = usernameTf.getText();
        String password = User.getUser(userName).getPassword();
        boolean isAdmin = false;

        if (authorizationChoiceBox.getValue().equals("Admin")) {
            isAdmin = true;
        }

        User user = new User(userName, password, false, isAdmin, name, surName);
        DB_CALLER_USER.updateUser(user);

        closeScene();
    }

    /**
     * Button action for cancel Button.
     */
    public void cancel() {
        closeScene();
    }

    /**
     * Method closes Popup.
     */
    @FXML
    public void closeScene() {
        Stage close = (Stage) submitBtn.getScene().getWindow();
        close.close();
    }

    /**
     * Constructor.
     *
     * @param user - userToEdit
     */
    public UserEditAdminController(User user) {
        this.user = user;
    }

    /**
     * Initialization.
     *
     * @param url - url
     * @param resourceBundle - resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surnameTf.setText(user.getSurName());
        nameTf.setText(user.getName());
        usernameTf.setText(user.getUserName());
        authorizationChoiceBox.getItems().addAll("Admin", "User");
        if (user.getIsAdmin()) {
            authorizationChoiceBox.setValue("Admin");
        } else {
            authorizationChoiceBox.setValue("User");
        }
    }
}
