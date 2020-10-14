package org.dhbw;

import backend.usability.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class UserEditAdminController extends Controller {

    @FXML
    public TextField surnameTf;
    @FXML
    public TextField nameTf;
    @FXML
    public TextField usernameTf;

    @FXML
    public ChoiceBox authorizationChoiceBox;
    @FXML
    public Button submitBtn;
    @FXML
    public Button cancelBtn;

    @FXML
    public void fillFields(User user) {
        surnameTf.setText(user.getSurName());
        nameTf.setText(user.getName());
        usernameTf.setText(user.getUserName());

        if (user.getIsAdmin() == true) {
            authorizationChoiceBox.setValue("Admin");
        } else if (user.getIsAdmin() == false) {
            authorizationChoiceBox.setValue("User");
        } else {
            authorizationChoiceBox.setValue("Fehler");
        }
    }

    @FXML
    public void submit() {
        String surName = surnameTf.getText();
        String name = nameTf.getText();
        String userName = usernameTf.getText();
        String password = User.getUser(userName).getPassword();
        boolean isAdmin = false;

        if (authorizationChoiceBox.getValue() == "Admin") {
            isAdmin = true;
        } else if (authorizationChoiceBox.getValue() == "User") {
            isAdmin = false;
        }

        User user = new User(userName, password, false, isAdmin, name, surName);
        CB_CALLER_USER.updateUser(user);

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
