package org.dhbw;

import backend.usability.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserInfoController extends Controller implements Initializable {

    @FXML
    public TextField surnameTf;
    @FXML
    public TextField nameTf;
    @FXML
    public TextField usernameTf;

    private User userToShow;

    public UserInfoController (User user) {
        this.userToShow= user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        surnameTf.setText(userToShow.getSurName());
        nameTf.setText(userToShow.getName());
        usernameTf.setText(userToShow.getUserName());
    }
}
