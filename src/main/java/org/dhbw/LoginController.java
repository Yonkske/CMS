package org.dhbw;

import backend.usability.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController extends Controller {

    @FXML
    private TextField usernameTf;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label meldungLbl;

    @FXML
    /**
     * This method is for a user logIn, it is checked whether the user is a admin.
     */
    public void logIn() throws IOException {

        String givenName = usernameTf.getText();
        String givenPassword = passwordField.getText();
        User user = DB_CALLER_USER.getUser(givenName);

        // Wenn der Null ist löst zuvor die
        if (user == null) {
            showError();
        } else {
            if (givenName.length() == 0 & givenPassword.length() == 0) {
                showError();
            } else if (givenName.length() == 0) {
                showError();
            } else if (givenPassword.length() == 0) {
                showError();
            } else if (checkPassword(givenPassword, DB_CALLER_USER.getEncryptedPassword(givenName)) & user.getIsInitial()) {
                Controller.user = User.getUser(givenName);
                openPopUpEditPassword(User.getUser(givenName));
            } else if (checkPassword(givenPassword, DB_CALLER_USER.getEncryptedPassword(givenName)) & !user.getIsInitial()) {
                Controller.user = User.getUser(givenName);
                switchToStartpage(User.getUser(givenName));
            } else {
                showError();
            }
        }
    }

    @FXML
    /**
     * This method sets a error message visible if one is needed.
     */
    private void showError() {
        meldungLbl.setVisible(true);
    }

    @FXML
    /**
     * This method forwards to the Startpage.
     */
    private void switchToStartpage(User user) throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

    @FXML
    /**
     * this methode open the PopUp to edit the password if the user logs in for the first time
     */
    private void openPopUpEditPassword(User user) throws IOException {
        FXMLFactory.setRoot("ChangePassword");
    }

    @FXML
    /**
     * this methode terminates the program
     */
    public void exit(ActionEvent event) {
        Platform.exit();
    }
}
