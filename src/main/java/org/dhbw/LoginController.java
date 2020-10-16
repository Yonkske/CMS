package org.dhbw;

import backend.usability.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

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
    public void logIn() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {

        String givenName = usernameTf.getText();
        String givenPassword = passwordField.getText();
        String givenPasswordEncrypt = super.encryptPassword(givenPassword);

        User user = CB_CALLER_USER.getUser(givenName);

        if (user == null) {
            showError();
        } else {
            if (givenName.length() == 0 & givenPassword.length() == 0) {
                showError();
            } else if (givenName.length() == 0) {
                showError();
            } else if (givenPassword.length() == 0) {
                showError();
            } else if (CB_CALLER_USER.checkUser(givenName, givenPassword) & user.getIsInitial()) {
                // TODO: Wenn nur noch codierte passwörter gespeichert sind: CB_CALLER_USER.checkUser(givenName, givenPasswordEncrypt)
                openPopUpEditPassword(User.getUser(givenName));
                super.user = User.getUser(givenName);
            } else if (CB_CALLER_USER.checkUser(givenName, givenPassword) & !user.getIsInitial()) {
                // TODO: Wenn nur noch codierte passwörter gespeichert sind: CB_CALLER_USER.checkUser(givenName, givenPasswordEncrypt)
                switchToStartpage(User.getUser(givenName));
                super.user = User.getUser(givenName);
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