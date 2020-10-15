package org.dhbw;


import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;



public class UserViewAdminController extends Controller  {

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

    /**
     *
     * @param user
     */
    public void fillTextfields(User user) {
        // TODO: Methode testen

        this.currentUser = user;

        String authorisation;
        if (user.getIsAdmin() == true) {
            authorisation = "Admin";
        } else if (user.getIsAdmin() == false) {
            authorisation = "User";
        } else {
            authorisation = "failure";
        }

        surnameTf.setText(user.getSurName());
        nameTf.setText(user.getName());
        authorizationTf.setText(authorisation);
        usernameTf.setText(user.getUserName());
    }


    @FXML
    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void deleteUser(ActionEvent actionEvent) throws IOException {
        // TODO: Test der Methode

        NotificationController notificationController = new NotificationController(currentUser, PAGE_NAME);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notification.fxml"));
        loader.setController(notificationController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    @FXML
    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void switchToUserEditAdmin(ActionEvent actionEvent) throws IOException {
        // TODO: Test der Methode
        String userName = usernameTf.getText();
        //User userToGet = User.getUser(userName);
        User user1 = User.getUser("admin");

        UserEditAdminController UserEditAdminController = new UserEditAdminController(user1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEditAdmin.fxml"));
        loader.setController(UserEditAdminController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();

    }

    @FXML
    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void switchToPasswordEditAdmin(ActionEvent actionEvent) throws IOException {
        // TODO: Methode testen
        User userToGet = User.getUser(usernameTf.getText());

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


