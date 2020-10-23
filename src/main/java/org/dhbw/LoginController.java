package org.dhbw;

import backend.database.DbCallerUser;
import backend.database.DbConnector;
import backend.usability.User;
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
import java.sql.SQLException;



public class LoginController extends Controller {

    @FXML
    private TextField usernameTf;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Label meldungLbl;

    /**
     * Creates a new login controller
     *
     * @param isFirstCall true to start a new database connection, false if you dont want a new database connection
     * @throws SQLException
     */
    public LoginController(Boolean isFirstCall) throws SQLException {
        super();
        if (isFirstCall) {
            new DbConnector().startConnection();

        }
    }

    /**
     * This method is for a user logIn, it is checked whether the user is a admin.
     */
    @FXML
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
            } else if (checkPassword(givenPassword, DB_CALLER_USER.getEncryptedPassword(givenName))
                    && user.getIsInitial()) {
                Controller.user = DB_CALLER_USER.getUser(givenName);
                openPopUpEditPassword(DB_CALLER_USER.getUser(givenName));
            } else if (checkPassword(givenPassword, DB_CALLER_USER.getEncryptedPassword(givenName))
                    && !user.getIsInitial()) {
                Controller.user = DB_CALLER_USER.getUser(givenName);
                switchToStartpage(Controller.user);
            } else {
                showError();
            }
        }
    }

    /**
     * This method sets a error message visible if one is needed.
     */
    @FXML
    private void showError() {
        meldungLbl.setVisible(true);
    }

    /**
     * This method forwards to the Startpage.
     *
     * @param user - userToLogin
     */
    @FXML
    private void switchToStartpage(User user) throws IOException {
        FXMLFactory.setRoot("Startpage", true);
    }

    /**
     * this methode open the PopUp to edit the password if the user logs in for the first time.
     *
     * @param user - userToLogin
     */
    @FXML
    private void openPopUpEditPassword(User user) throws IOException {
        ChangePasswordController changePasswordController = new ChangePasswordController(
                new DbCallerUser().getUser(usernameTf.getText()));
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
}
