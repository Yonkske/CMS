package org.dhbw;


import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
    private Button yesBtn;
    @FXML
    private Button noBtn;

    private User userToEdit;

    public UserViewAdminController(User user) {
        this.userToEdit = user;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // TODO: Methode testen

        getData();
    }

    @FXML
    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void deleteUser(ActionEvent actionEvent) throws IOException {
        // TODO: Test der Methode
        String userName = usernameTf.getText();

        NotificationController NotificationController = new NotificationController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notification.fxml"));
        loader.setController(NotificationController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();


        /*yesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CB_CALLER_USER.deleteUser(userToEdit); // TODO: Diese Zeile ausführen lassen.
                new Controller().closeScene();
            }
        });

        noBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Controller().closeScene();
            }
        });*/
    }

    @FXML
    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void switchToUserEditAdmin(ActionEvent actionEvent) throws IOException {
        // TODO: Test der Methode
        UserEditAdminController UserEditAdminController = new UserEditAdminController(userToEdit);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEditAdmin.fxml"));
        loader.setController(UserEditAdminController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setOnHiding(windowEvent -> {
            this.userToEdit = User.getUser(usernameTf.getText());
            this.getData();});
        scene.getWindow().sizeToScene();
        stage.show();
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
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordEditAdmin.fxml"));
        Parent root = loader.load();
        //PasswordEditAdminController.showUserName(PasswordEditAdminController);
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }
}


