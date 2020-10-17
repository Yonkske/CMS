package org.dhbw;

import backend.database.DbCallerUser;
import backend.usability.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.IOException;


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
    private User userToCangePassword;
    private String callingPage;

    /**
     * Constructor.
     *
     * @param user
     */
    public ChangePasswordController(User user, String callingPage) {
        this.userToCangePassword = user;
        this.callingPage = callingPage;

        if (callingPage.equals("UserAdmin")) {
            oldPasswordPf.setEditable(false);
            oldPasswordPf.setPromptText("Keine Eingabe notwendig");
        }
    }

    public void submit() throws IOException {
        boolean oldPwCheck = super.checkPassword(oldPasswordPf.getText(), userToCangePassword.getPassword());
        boolean enteredPwCheck = this.checkNewPasswords();
        boolean noEmptyInputCheck = this.checkNoEmptyInput();

        if (callingPage.equals("Login")) {
            if (oldPwCheck && enteredPwCheck && noEmptyInputCheck) {
                User.changePassword(userToCangePassword.getUserName(), super.encryptPassword(newPasswordPf.getText()));

                userToCangePassword.setIsInitial(false);
                this.switchToStartpage(new DbCallerUser().getUser(userToCangePassword.getUserName()));

                this.closeScene();
            } else {
                this.showError();
            }
        } else if (callingPage.equals("UserAdmin")) {

            if (enteredPwCheck && noEmptyInputCheck) {
                User.changePassword(userToCangePassword.getUserName(), super.encryptPassword(newPasswordPf.getText()));
                userToCangePassword.setIsInitial(true);
            }
        } else {
            this.showError();
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
//        meldungLbl.setVisible(true); // TODO: meldungLbl einfügen!
    }

    /**
     * This method forwards to the Startpage.
     */
    @FXML
    private void switchToStartpage(User user) throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

}
