package org.dhbw;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;

import java.awt.*;

public class ChangePasswordController {
    @FXML public Label oldPasswordLbl;
    @FXML public Label newPasswordLbl;
    @FXML public Label repeatPasswordLbl;
    @FXML public PasswordField oldPasswordPf;
    @FXML public PasswordField newPasswordPf;
    @FXML public PasswordField repeatPasswordPf;
    @FXML public Button cancelBtn;
    @FXML public Button submitBtn;
}
