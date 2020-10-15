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
    public void logIn() throws IOException {
        // TODO: Check for ADMIN, Hashing the PW

        String givenName = usernameTf.getText();
        String givenPassword = passwordField.getText();

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
            } else if (CB_CALLER_USER.checkUser(givenName, givenPassword) == true & user.getIsInitial() == true) {
                openPopUpEditPassword(User.getUser(givenName));
                new Controller().user = User.getUser(givenName);
            } else if (CB_CALLER_USER.checkUser(givenName, givenPassword) == true & user.getIsInitial() == false) {
                switchToStartpage(User.getUser(givenName));
                new Controller().user = User.getUser(givenName);
            } else {
                showError();
            }
        }
    }

    public String verschlüsseltPasswort(String password) throws InvalidKeySpecException, NoSuchAlgorithmException {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];

        random.nextBytes(salt);
        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);

        SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        String hash = String.valueOf(factory.generateSecret(spec).getEncoded());
        return hash;
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
     *
     */
    private void openPopUpEditPassword(User user) throws IOException {
        FXMLFactory.setRoot("ChangePassword");
    }

    @FXML
    /**
     *
     */
    public void exit(ActionEvent event) {
        Platform.exit();
    }
}