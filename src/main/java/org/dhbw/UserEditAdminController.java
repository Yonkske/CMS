package org.dhbw;

import backend.usability.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class UserEditAdminController extends Controller implements Initializable {

    @FXML
    public TextField surnameTf;
    @FXML
    public TextField nameTf;
    @FXML
    public TextField usernameTf;

    @FXML
    public ChoiceBox<String> authorizationChoiceBox;
    @FXML
    public Button submitBtn;
    @FXML
    public Button cancelBtn;
    private User user;

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

    public UserEditAdminController(User user) {
        this.user = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println(user.getName() + " Hallo");
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
