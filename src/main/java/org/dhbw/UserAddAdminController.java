package org.dhbw;

import backend.usability.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;

public class UserAddAdminController extends Controller implements Initializable {

    @FXML
    private TextField surnameTf;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField usernameTf;
    @FXML
    private TextField initialPasswordTf;

    @FXML
    private ChoiceBox<String> authorizationChoiceBox;

    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authorizationChoiceBox.getItems().addAll("Admin", "User");
        authorizationChoiceBox.setValue("User");
    }

    public void submit() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String surName = surnameTf.getText();
        String name = nameTf.getText();
        String userName = usernameTf.getText();
        String passwordNotEncrypted = initialPasswordTf.getText();

        boolean isAdmin = false;
        if (authorizationChoiceBox.getValue() == "Admin") {
            isAdmin = true;
        } else if (authorizationChoiceBox.getValue() == "User") {
            isAdmin = false;
        }

        String passwordEncrypted = super.encryptPassword(passwordNotEncrypted);

        User user = new User(userName, passwordEncrypted, true, isAdmin, name, surName);
        CB_CALLER_USER.insertUser(user);

        closeScene();
    }

    public void cancel() {
        closeScene();
    }

    @FXML
    public void closeScene() {
        Stage close = new Stage();
        close = (Stage) submitBtn.getScene().getWindow();
        close.close();
    }
}
