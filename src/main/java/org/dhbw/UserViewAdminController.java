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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: Methode testen
        this.getData();
    }

    @FXML
    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void deleteUser(ActionEvent actionEvent) throws IOException {
        // TODO: Test der Methode
        openPopup(new NotificationController(userToEdit, PAGE_NAME), "Notification.fxml", false, true);
    }

    @FXML
    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void switchToUserEditAdmin(ActionEvent actionEvent) throws IOException {
        // TODO: Test der Methode
        openPopup(new UserEditAdminController(userToEdit), "UserEditAdmin.fxml", true, false);
    }

    private void getData() {
        String authorisation;
        if (userToEdit.getIsAdmin() == true) {
            authorisation = "Admin";
        } else if (userToEdit.getIsAdmin() == false) {
            authorisation = "User";
        } else {
            authorisation = "failure";
        }
        surnameTf.setText(userToEdit.getSurName());
        nameTf.setText(userToEdit.getName());
        authorizationTf.setText(authorisation);
        usernameTf.setText(userToEdit.getUserName());
    }

    @FXML
    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void switchToPasswordEditAdmin(ActionEvent actionEvent) throws IOException {
        // TODO: Methode testen
        openPopup(new PasswordEditAdminController(), "PasswordEditAdmin.fxml", false, true);
    }

    private void openPopup(Controller controller, String fxmlName, boolean onHidingRefresh, boolean onHindingClose) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        if (onHidingRefresh) {
            stage.setOnHiding(windowEvent -> {
                this.userToEdit = User.getUser(usernameTf.getText());
                this.getData();});
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

    protected void closeScene(){
        Stage stClose = (Stage) nameTf.getScene().getWindow();
        stClose.close();
    }
}


