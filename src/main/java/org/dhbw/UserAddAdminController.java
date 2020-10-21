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

            if (authorizationChoiceBox.getValue().equals("Admin")) {
                isAdmin = true;
            }

            if ((userName.length() > 0 && initialPasswordTf.getText().length() > 0
                    && repeatInitialPasswordTf.getText().length() > 0)
                    && checkForRegex(initialPasswordTf.getText(), userName)) {
                String passwordEncrypted = super.encryptPassword(initialPasswordTf.getText());
                User user = new User(userName, passwordEncrypted, true, isAdmin, name, surName);
                DB_CALLER_USER.insertUser(user);
                closeScene();
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

}
