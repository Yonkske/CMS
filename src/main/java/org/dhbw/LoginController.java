package org.dhbw;

import backend.database.DbCallerUser;
import backend.usability.User;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    public void logIn() {
        // TODO: Check for ADMIN, Hashing the PW

        String givenName = usernameTf.getText();
        String givenPassword = passwordField.getText();
        System.out.println(givenName + " " + givenPassword);
        if (CB_CALLER_USER.checkUser(givenName, givenPassword) == true) {
            try {
                switchToStartpage(User.getUser(givenName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showError();
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

        FXMLFactory.setRoot("StartpageAdmin");
    }

    @FXML
    /**
     *
     */
    private void openPopUpEditPassword(User user) {

    }

    @FXML
    /**
     *
     */
    public void exit(ActionEvent event) {
        Platform.exit();
    }

}
