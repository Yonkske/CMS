package org.dhbw;

import backend.usability.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;



public class PasswordEditAdminController extends Controller {

    @FXML
    private PasswordField newPasswordPf;
    @FXML
    private PasswordField passwordRepeatPf;
    @FXML
    private Button submitBtn;
    @FXML
    private Button cancelBtn;
    @FXML
    private Label meldungLbl;
    private final User userToChangePw;

    /**
     * Constructor.
     *
     * @param user - userToChangePw
     */
    public PasswordEditAdminController(User user) {
        this.userToChangePw = user;
    }

    /**
     * Button Action for submit button.
     */
    public void submit() {
        boolean enteredPwCheck = this.checkNewPasswords();
        boolean noEmptyInputCheck = this.checkNoEmptyInput();
        boolean checkPasswordCriteria = this.checkPasswordCriteria(newPasswordPf.getText());

        if (enteredPwCheck && noEmptyInputCheck && checkForRegex(newPasswordPf.getText()) && checkPasswordCriteria) {
            userToChangePw.setNewPassword(super.encryptPassword(newPasswordPf.getText()));
            userToChangePw.setIsInitial(true);
            DB_CALLER_USER.updateUser(userToChangePw);
            this.closeScene();
        } else {
            this.showError();
        }
    }

    /**
     * Method checks if all fields are filled.
     *
     * @return boolean
     */
    private boolean checkNoEmptyInput() {
        if (newPasswordPf.getText().length() != 0 && passwordRepeatPf.getText().length() != 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Mehtod checks if both given passwords are the same.
     *
     * @return boolean
     */
    private boolean checkNewPasswords() {
        if (newPasswordPf.getText().equals(passwordRepeatPf.getText())) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Method checks if the password has a length of at least 5.
     * Additionally the password should not contain empty spaces.
     * @param newPassword - new password
     * @return boolean
     */
    private boolean checkPasswordCriteria(String newPassword) {
        if (newPassword.length() >= 5 && !newPassword.contains(" ")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Action for the Cancel Button.
     */
    public void cancel() {
        this.closeScene();
    }

    /**
     * Method closes the Popup.
     */
    @FXML
    public void closeScene() {
        Stage close = new Stage();
        close = (Stage) submitBtn.getScene().getWindow();
        close.close();
    }

    /**
     * When a error warning will be implemented this method will show it when needed.
     */
    public void showError() {
        meldungLbl.setVisible(true);
    }


}
