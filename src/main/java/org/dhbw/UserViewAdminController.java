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
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();


        /*yesBtn.setOnAction(new EventHandler<ActionEvent>() {
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
        User user = User.getUser(usernameTf.getText());

        //PasswordEditAdminController PasswordEditAdminController = new PasswordEditAdminController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("PasswordEditAdmin.fxml"));
        //loader.setController(PasswordEditAdminController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();

        //PasswordEditAdminController.showUserName(user);

    }
}


