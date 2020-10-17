package org.dhbw;


import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserViewAdminController extends Controller implements Initializable {

    @FXML
    private TextField surnameTf;
    @FXML
    private TextField nameTf;
    @FXML
    private TextField authorizationTf;
    @FXML
    private TextField usernameTf;

    @FXML
    private User currentUser;
    @FXML
    private final String PAGE_NAME = "UserAdmin";

    private User userToEdit;

    public UserViewAdminController(User user) {
        this.userToEdit = user;
    }

    /**
     *
     * Methode from the interface Initializable that auto generates the page
     *
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: Methode testen
        this.getData();
    }

    /**
     * this methode open the Notification PopUp to delete a selected user
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void deleteUser(ActionEvent actionEvent) throws IOException {
        // TODO: Test der Methode
        openPopup(new NotificationController(userToEdit, PAGE_NAME), "Notification.fxml", false, true);
    }

    /**
     * this methode open the PopUp to edit the selected user
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void switchToUserEditAdmin(ActionEvent actionEvent) throws IOException {
        // TODO: Test der Methode
        openPopup(new UserEditAdminController(userToEdit), "UserEditAdmin.fxml", true, false);
    }

    /**
     * This method opens a popup where you can change your own password.
     *
     * @throws IOException
     */
    public void swapToChangePassword() throws IOException {
        this.openPopup(new PasswordEditAdminController(user), "PasswordEditAdmin.fxml", false, true);
    }

    /**
     * this methode displays the selected user
     *
     */
    private void getData() {
        String authorisation;
        if (userToEdit.getIsAdmin()) {
            authorisation = "Admin";
        } else if (!userToEdit.getIsAdmin()) {
            authorisation = "User";
        } else {
            authorisation = "failure";
        }
        surnameTf.setText(userToEdit.getSurName());
        nameTf.setText(userToEdit.getName());
        authorizationTf.setText(authorisation);
        usernameTf.setText(userToEdit.getUserName());
    }

    /**
     * this methode open the PopUp to edit the password from a selected user
     * @param actionEvent
     * @throws IOException
     */
    @FXML
    public void switchToPasswordEditAdmin(ActionEvent actionEvent) throws IOException {
        // TODO: Methode testen
        openPopup(new PasswordEditAdminController(userToEdit), "PasswordEditAdmin.fxml", false, false);
    }

    /**
     * this method open the popUps
     *
     * @param controller
     * @param fxmlName
     * @param onHidingRefresh
     * @param onHindingClose
     * @throws IOException
     */
    private void openPopup(Controller controller, String fxmlName, boolean onHidingRefresh, boolean onHindingClose) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        if (onHidingRefresh) {
            stage.setOnHiding(windowEvent -> {
                this.userToEdit = User.getUser(usernameTf.getText());
                this.getData();
            });
        } else if (onHindingClose) {
            stage.setOnHiding(windowEvent -> this.closeScene());
        }
        stage.getIcons().add(new Image(App.class.getResourceAsStream("icons/favicon1.jpg")));
        stage.setTitle("CMS - Configuration Management System");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    /**
     * this methode close the PopUp after an action by the user
     */
    protected void closeScene() {
        Stage stClose = (Stage) nameTf.getScene().getWindow();
        stClose.close();
    }
}


