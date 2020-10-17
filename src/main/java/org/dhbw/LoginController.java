package org.dhbw;

import backend.database.DbCallerUser;
import backend.usability.User;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

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

    /**
     * This method forwards to the Startpage.
     */
    @FXML
    private void switchToStartpage(User user) throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

    @FXML
    /**
     * this methode open the PopUp to edit the password if the user logs in for the first time
     */
    private void openPopUpEditPassword(User user) throws IOException {
        ChangePasswordController changePasswordController = new ChangePasswordController(new DbCallerUser().getUser(usernameTf.getText()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ChangePassword.fxml"));
        loader.setController(changePasswordController);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("icons/favicon1.jpg")));
        stage.setTitle("CMS - Configuration Management System");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    @FXML
    /**
     * this methode terminates the program
     */
    public void exit(ActionEvent event) {
        Platform.exit();
    }
}
