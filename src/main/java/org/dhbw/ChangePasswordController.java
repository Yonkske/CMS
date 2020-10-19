package org.dhbw;

import backend.usability.User;
import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ChangePasswordController extends Controller {

    @FXML
    private PasswordField oldPasswordPf;
    @FXML
    private PasswordField newPasswordPf;
    @FXML
    private PasswordField repeatPasswordPf;
    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private Label meldungLbl;
    private User userToCangePassword;

    /**
     * Constructor.
     *
     * @param user - The password of this user will be changed.
     */
    public ChangePasswordController(User user) {
        this.userToCangePassword = user;
    }

    /**
     * Action when submit Button is pushed.
     *
     * @throws IOException
     */
    @FXML
    public void submit() throws IOException {
        boolean oldPwCheck = super.checkPassword(oldPasswordPf.getText(), userToCangePassword.getPassword());
        boolean enteredPwCheck = this.checkNewPasswords();
        boolean noEmptyInputCheck = this.checkNoEmptyInput();

        if (oldPwCheck && enteredPwCheck && noEmptyInputCheck) {
            userToCangePassword.setNewPassword(super.encryptPassword(newPasswordPf.getText()));

            userToCangePassword.setIsInitial(false);
            DB_CALLER_USER.updateUser(userToCangePassword);
            this.switchToStartpage(DB_CALLER_USER.getUser(userToCangePassword.getUserName()));

            this.closeScene();
        } else {
            this.showError();
        }

    }

    /**
     * Action for the Cancel Button.
     */
    @FXML
    public void cancel() {
        this.closeScene();
    }

    /**
     * Method closes the Popup.
     */
    @FXML
    private void closeScene() {
        Stage close = (Stage) submitBtn.getScene().getWindow();
        close.close();
    }

    private boolean checkNewPasswords() {
        if (newPasswordPf.getText().equals(repeatPasswordPf.getText())) {
            return true;
        } else {
            return false;
        }
    }
    private boolean checkNoEmptyInput() {
        if (oldPasswordPf.getText().length() !=0 && newPasswordPf.getText().length() !=0 && repeatPasswordPf.getText().length() !=0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * When a error warning will be implemented this method will show it when needed.
     */
    public void showError() {
        meldungLbl.setVisible(true);
    }

    /**
     * This method forwards to the Startpage.
     */
    private void switchToStartpage(User user) throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

}
