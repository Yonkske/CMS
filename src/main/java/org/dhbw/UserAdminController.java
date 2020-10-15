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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UserAdminController extends Controller implements Initializable {

    @FXML
    private TextField searchTextField;

    @FXML
    private Button searchBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button editBtn;

    @FXML
    private TableView<User> userTable;
    @FXML
    private TableColumn<User, String> userColumn;
    @FXML
    private TableColumn<User, String> rightColumn;

    @FXML
    private Label adminLbl;


    /**
     * Methode from the interface Initializable that auto generates the page on
     * start
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            userTable.getItems().setAll(CB_CALLER_USER.getAllUsers());
            userColumn.setCellValueFactory(new PropertyValueFactory<User, String>("UserName"));
            rightColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Right"));
        } catch (Exception e) {
            e.printStackTrace();
        }

        adminLbl.setText(super.user.getSurName() + ", " + super.user.getName() + " (Admin)");

    }
    @FXML
    public void clickAction(MouseEvent mouseEvent) {

        if (mouseEvent.getClickCount() == 1) {
            editBtn.setDisable(true);
            deleteBtn.setDisable(true);
        } else if (mouseEvent.getClickCount() == 2) {
            try {
                showUser();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void showUser() throws IOException {

        User userToShow = userTable.getSelectionModel().getSelectedItem();

        UserViewAdminController UserViewAdminController = new UserViewAdminController(userToShow);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserViewAdmin.fxml"));
        loader.setController(UserViewAdminController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    public void addUser() throws IOException {
        // TODO: Test method

        UserAddAdminController UserAddAdminController = new UserAddAdminController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserAddAdmin.fxml"));
        loader.setController(UserAddAdminController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    public void editUser() throws IOException {
        // TODO: Test der Methode
        User userToEdit = userTable.getSelectionModel().getSelectedItem();

        UserEditAdminController UserEditAdminController = new UserEditAdminController(userToEdit);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UserEditAdmin.fxml"));
        loader.setController(UserEditAdminController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    public void deleteUser() throws IOException {
        // TODO: Test der Methode
        //String userName = usernameTf.getText();
        User userToDelete = userTable.getSelectionModel().getSelectedItem();

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
                CB_CALLER_USER.deleteUser(userToDelete); // TODO: Diese Funktion ausführen.
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

    public void swapToStartpage(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

    public void swapToCIT(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("CIT");
    }

    public void swapToStatistic(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("Statistic");
    }

    public void swapToBenutzer(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("UserAdmin");
    }
}

