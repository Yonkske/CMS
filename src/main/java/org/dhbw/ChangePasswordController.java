package org.dhbw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

import java.io.IOException;

public class ChangePasswordController extends Controller {

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

    public void switchToStartpage(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("Startpage");
    }
}
