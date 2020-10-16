package org.dhbw;

import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    @FXML
    private final String PAGE_NAME = "UserAdmin";


    /**
     * Methode from the interface Initializable that auto generates the page on
     * start
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        getData();

    }

    private void getData() {
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
        boolean userSelected = false;
        if (Objects.nonNull(userTable.getSelectionModel().getSelectedItem())) {
            userSelected = true;
        }

        if (userSelected) {
            editBtn.setDisable(false);
            deleteBtn.setDisable(false);
        }
        if (mouseEvent.getClickCount() == 2) {
            try {
                showUser();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void disableButtons() {
        editBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }


    public void showUser() throws IOException {
        if (Objects.nonNull(userTable.getSelectionModel().getSelectedItem())) {
            System.out.println("Pop up");
            openPopup(new UserViewAdminController(userTable.getSelectionModel().getSelectedItem()), "UserViewAdmin.fxml", true, false);
            this.disableButtons();
        }
    }

    public void addUser() throws IOException {
        // TODO: Test method
        openPopup(new UserAddAdminController(), "UserAddAdmin.fxml", true, true);
        this.disableButtons();
    }

    public void editUser() throws IOException {
        // TODO: Test der Methode
        if (Objects.nonNull(userTable.getSelectionModel().getSelectedItem())) {
            openPopup(new UserEditAdminController(userTable.getSelectionModel().getSelectedItem()), "UserEditAdmin.fxml", true, false);
            this.disableButtons();
        }
    }

    public void deleteUser() throws IOException {
        // TODO: Test der Methode
        if (Objects.nonNull(userTable.getSelectionModel().getSelectedItem())) {
            openPopup(new NotificationController(userTable.getSelectionModel().getSelectedItem(), PAGE_NAME), "Notification.fxml", false, true);
            this.disableButtons();
        }
    }

    private void openPopup(Controller controller, String fxmlName, boolean onHidingRefresh, boolean onHindingClose) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        if (onHidingRefresh) {
            stage.setOnHiding(windowEvent -> this.getData());
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

    protected void closeScene() {
        Stage stClose = (Stage) adminLbl.getScene().getWindow();
        stClose.close();
    }
}