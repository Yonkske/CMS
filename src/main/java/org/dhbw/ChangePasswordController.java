package org.dhbw;

import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChangePasswordController extends Controller implements Initializable {

    @FXML
    public Label oldPasswordLbl;
    @FXML
    public Label newPasswordLbl;
    @FXML
    public Label repeatPasswordLbl;
    @FXML
    public PasswordField oldPasswordPf;
    @FXML
    public PasswordField newPasswordPf;
    @FXML
    public PasswordField repeatPasswordPf;
    @FXML
    public Button cancelBtn;
    @FXML
    public Button submitBtn;

    public ChangePasswordController(User user) {
    }

    public void switchToStartpage(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
