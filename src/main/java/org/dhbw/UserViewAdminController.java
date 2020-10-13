package org.dhbw;


import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class UserViewAdminController extends Controller {

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

    public void fillTextfields(User user) {

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


    public void deleteUser(ActionEvent actionEvent) throws IOException {
        String userName = usernameTf.getText();

        NotificationController NotificationController = new NotificationController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notification.fxml"));
        loader.setController(NotificationController);

        yesBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                CB_CALLER_USER.deleteUser(CB_CALLER_USER.getUser(userName));
                new Controller().closeScene();
            }
        });

        noBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                new Controller().closeScene();
            }
        });

    }

    public void switchToUserEditAdmin(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("UserEditAdmin");
    }

    public void switchToPasswordEditAdmin(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("PasswordEditAdmin");
    }

}


