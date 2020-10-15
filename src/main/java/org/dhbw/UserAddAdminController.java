package org.dhbw;

import backend.usability.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.ResourceBundle;

public class UserAddAdminController extends Controller implements Initializable {

    @FXML
    private TextField surnameTf;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField usernameTf;
    @FXML
    private TextField initialPasswordTf;
    @FXML
    private TextField repeatInitialPasswordTf;

    @FXML
    private ChoiceBox<String> authorizationChoiceBox;

    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private Label meldungLbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authorizationChoiceBox.getItems().addAll("Admin", "User");
        authorizationChoiceBox.setValue("User");
    }

    @FXML
    /**
     * Method checks if both given passwords are identical.
     * Method additionally checks if Username ans Password are given.
     * If both checks are true the given Password will be encrypt.
     * Additionally the new User will be saved in the Database.
     *
     * @throws InvalidKeySpecException
     * @throws NoSuchAlgorithmException
     */
    public void submit() throws InvalidKeySpecException, NoSuchAlgorithmException {
        String passwordNotEncrypted = initialPasswordTf.getText();
        String passwortRepeated = repeatInitialPasswordTf.getText();

        if (passwordNotEncrypted == passwortRepeated) {
            String surName = surnameTf.getText();
            String name = nameTf.getText();
            String userName = usernameTf.getText();

            boolean isAdmin = false;
            if (authorizationChoiceBox.getValue() == "Admin") {
                isAdmin = true;
            } else if (authorizationChoiceBox.getValue() == "User") {
                isAdmin = false;
            }

            if (userName.length() > 0 & passwordNotEncrypted.length() > 0 & passwortRepeated.length() > 0) {

                String passwordEncrypted = super.encryptPassword(passwordNotEncrypted);
                User user = new User(userName, passwordEncrypted, true, isAdmin, name, surName);
                CB_CALLER_USER.insertUser(user);

                closeScene();
            } else if (userName.length() == 0 & passwordNotEncrypted.length() == 0 & passwortRepeated.length() == 0) {
                showError();
                usernameTf.setText("");
                initialPasswordTf.setText("");
                repeatInitialPasswordTf.setText("");
            } else if (userName.length() > 0 & passwordNotEncrypted.length() == 0 & passwortRepeated.length() == 0) {
                showError();
                usernameTf.setText("");
                initialPasswordTf.setText("");
                repeatInitialPasswordTf.setText("");
            } else if (userName.length() == 0 & passwordNotEncrypted.length() > 0 & passwortRepeated.length() == 0) {
                showError();
                usernameTf.setText("");
                initialPasswordTf.setText("");
                repeatInitialPasswordTf.setText("");
            } else if (userName.length() == 0 & passwordNotEncrypted.length() == 0 & passwortRepeated.length() > 0) {
                showError();
                usernameTf.setText("");
                initialPasswordTf.setText("");
                repeatInitialPasswordTf.setText("");
            }
        } else {
            showError();
            initialPasswordTf.setText("");
            repeatInitialPasswordTf.setText("");
        }
    }

    /**
     * Action for the Cancel Button.
     */
    public void cancel() {
        closeScene();
    }

    @FXML
    /**
     * Method closes the Popup.
     */
    public void closeScene() {
        Stage close = new Stage();
        close = (Stage) submitBtn.getScene().getWindow();
        close.close();
    }

    /**
     * When a error warning will be implemented this method will show it when needed.
     */
    public void showError() {
        meldungLbl.setVisible(true); // TODO: meldungLbl einfügen!
    }
}
