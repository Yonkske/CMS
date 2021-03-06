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

    /**
     * Methode from the interface initializable that auto generates the PopUp to add a user.
     *
     * @Param url url
     * @Param resourceBundle resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        authorizationChoiceBox.getItems().addAll("Admin", "User");
        authorizationChoiceBox.setValue("User");
    }

    /**
     * Method checks if both given passwords are identical.
     * Method additionally checks if Username ans Password are given.
     * If both checks are true the given Password will be encrypt.
     * Additionally the new User will be saved in the Database.
     *
     * @throws InvalidKeySpecException  - Exception
     * @throws NoSuchAlgorithmException - Exception
     */
    @FXML
    public void submit() throws InvalidKeySpecException, NoSuchAlgorithmException {

        if (initialPasswordTf.getText().equals(repeatInitialPasswordTf.getText())) {
            String surName = surnameTf.getText();
            String name = nameTf.getText();
            String userName = usernameTf.getText();
            boolean isAdmin = false;
            boolean checkPasswordCriteria = this.checkPasswordCriteria(initialPasswordTf.getText());

            if (authorizationChoiceBox.getValue().equals("Admin")) {
                isAdmin = true;
            }

            if ((userName.length() > 0 && initialPasswordTf.getText().length() > 0
                    && repeatInitialPasswordTf.getText().length() > 0)
                    && checkForRegex(initialPasswordTf.getText(), userName) && checkPasswordCriteria) {
                String passwordEncrypted = super.encryptPassword(initialPasswordTf.getText());
                User user = new User(userName, passwordEncrypted, true, isAdmin, name, surName);

                int returnCode = DB_CALLER_USER.insertUser(user);
                if (returnCode == 0) {
                    closeScene();
                } else if (returnCode == 1) {
                    showError("Benutzername vergeben!");
                } else if (returnCode == 2) {
                    showError("Maximal 255 Zeichen!");
                } else {
                    showError();
                }
            } else {
                showError();
                clearPasswordAndUser();
            }
        } else {
            showError();
            initialPasswordTf.setText("");
            repeatInitialPasswordTf.setText("");
        }
    }

    /**
     * Methode is there space in the string
     * @param initialPassword - String to be checked
     * @param userName - String to be checked
     * @return true when both are true
     */

    private boolean checkForRegex(String initialPassword, String userName) {
        return !initialPassword.matches("^[\\s]+$") && !userName.matches("^[\\s]+$");
    }

    /**
     * Action for the Cancel Button.
     */
    public void cancel() {
        closeScene();
    }

    /**
     * Method closes the Popup.
     */
    @FXML
    public void closeScene() {
        Stage  close = (Stage) submitBtn.getScene().getWindow();
        close.close();
    }

    /**
     * When a error warning will be implemented this method will show it when needed.
     */
    public void showError() {
        meldungLbl.setText("Eingabe überprüfen");
        meldungLbl.setVisible(true);
    }

    /**
     * Shows a custom error message.
     *
     * @param errorMessage String - error message to be displayed
     */
    public void showError(String errorMessage) {
        meldungLbl.setText(errorMessage);
        meldungLbl.setVisible(true);
    }

    /**
     * Method clears password and user textFields.
     */
    public void clearPasswordAndUser() {
        usernameTf.setText("");
        initialPasswordTf.setText("");
        repeatInitialPasswordTf.setText("");
    }

    /**
     * Method checks if the password has a length of at least 5.
     * Additionally the password should not contain empty spaces.
     * Additionally the new password should not equal the old password.
     * @param password - new password
     * @return boolean
     */
    private boolean checkPasswordCriteria(String password) {
        if (password.length() >= 5 && !password.contains(" ")) {
            return true;
        } else {
            return false;
        }
    }

}
