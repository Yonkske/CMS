package org.dhbw;


import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

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
        // TODO: Methode testen

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
        // TODO: Test der Methode
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
        // TODO: Test der Methode
        //User user = User.getUser(usernameTf.getText());
        User user = User.getUser("123");
        System.out.println(user.getUserName());


        UserEditAdminController UserEditAdminController = new UserEditAdminController();
        UserEditAdminController.fillFields(user);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEditAdmin.fxml"));
        loader.setController(UserEditAdminController);


    }

    public void switchToPasswordEditAdmin(ActionEvent actionEvent) throws IOException {
        // TODO: Methode testen
        User user = User.getUser(usernameTf.getText());

        PasswordEditAdminController PasswordEditAdminController = new PasswordEditAdminController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordEditAdmin.fxml"));
        loader.setController(PasswordEditAdminController);

 //       PasswordEditAdminController.showUserName(user);

    }

}


