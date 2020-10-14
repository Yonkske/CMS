package org.dhbw;


import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class UserViewAdminController extends Controller implements Initializable  {

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

    /**
     *
     * @param user
     */
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

    /**
     *
     * @param actionEvent
     * @throws IOException
     */
    public void switchToUserEditAdmin(ActionEvent actionEvent) throws IOException {
        // TODO: Test der Methode
        User user = User.getUser(usernameTf.getText());

        UserEditAdminController UserEditAdminController = new UserEditAdminController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEditAdmin.fxml"));
        loader.setController(UserEditAdminController);

 //       UserEditAdminController.showUser(user);
    }
    @FXML
    /**
     *
     * @throws IOException
     */
    public void switchToPasswordEditAdmin() throws IOException {
        // TODO: Methode testen
        //User user = User.getUser(usernameTf.getText());

        PasswordEditAdminController fenster = new PasswordEditAdminController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordEditAdmin.fxml"));
        loader.setController(fenster);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();

 //       PasswordEditAdminController.showUserName(user);

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}


