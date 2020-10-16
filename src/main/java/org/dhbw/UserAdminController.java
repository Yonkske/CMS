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
import java.util.ArrayList;
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
    @FXML
    private ComboBox<String> filterUser;
    private ArrayList<User> allUsers = null;


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
        this.allUsers = CB_CALLER_USER.getAllUsers();
        try {
            userTable.getItems().setAll(allUsers);
            userColumn.setCellValueFactory(new PropertyValueFactory<User, String>("UserName"));
            rightColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Right"));
            filterUser.getItems().addAll("Rechte", "User", "Admin");
            filterUser.setValue("Rechte");
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
        } else if (mouseEvent.getClickCount() == 2) {
            try {
                showUser();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (!userSelected) {
            this.disableButtons();
        }
    }

    private void disableButtons() {
        editBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }


    public void showUser() throws IOException {
        if (Objects.nonNull(userTable.getSelectionModel().getSelectedItem())) {
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

    private void closeScene() {
        Stage stClose = (Stage) adminLbl.getScene().getWindow();
        stClose.close();
    }

    public ArrayList<User> getAllUserSearchValue(String searchValue) {
        ArrayList<User> answerListAdmin = new ArrayList<>();
        ArrayList<User> answerListUser = new ArrayList<>();

        for (User u : allUsers) {
            if (u.getIsAdmin()) {
                answerListAdmin.add(u);
            } else {
                answerListUser.add(u);
            }
        }

        if (searchValue.equals("Admin")) {
            return answerListAdmin;
        } else {
            return answerListUser;
        }
    }

    private ArrayList<User> getListOfAdmins() {
        ArrayList<User> list = new ArrayList<>() ;
        for (User u : allUsers) {
            if (user.getIsAdmin()) {
                list.add(u);
            }
        }
        return list;
    }


    @FXML
    private void setTableContent(ArrayList<User> user) {
        userTable.getItems().setAll(user);
        userColumn.setCellValueFactory(new PropertyValueFactory<User, String>("UserName"));
        rightColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Right"));
    }

    public ArrayList<User> getAllWithFilterAndSearch(String selectedFilter, String searchValue) {
        ArrayList<User> outputList = new ArrayList<>();
        ArrayList<User> filteredUserList = this.getAllUserSearchValue(selectedFilter);

        int amountFilteredUsers = filteredUserList.size();

        for (User u : filteredUserList) {

            if (u.getUserName().contains(searchValue)) {
                outputList.add(u);
            }
        }


        return outputList;
    }

    @FXML
    public void setTableWithFilterAndSearch() {
        String selectedUser = filterUser.getSelectionModel().getSelectedItem();
        String searchValue = searchTextField.getText();

        if (selectedUser.equals("Rechte")) {
            setTableContent(this.getAllUserSearchValue(searchValue));
        } else {
            setTableContent(this.getAllWithFilterAndSearch(selectedUser, searchValue));
        }
    }
}