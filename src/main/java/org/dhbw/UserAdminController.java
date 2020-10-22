package org.dhbw;

import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class UserAdminController extends MainPagesController {

    @FXML
    private TextField searchTf;
    @FXML
    private Button searchBtn;
    @FXML
    private Button addBtn;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button showUserBtn;

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
    @FXML
    private Label statusLbl;
    private ArrayList<User> allUsers = new ArrayList<>();

// Following are to show correct data on the user page

    /**
     * Methode from the interface Initializable that auto generates the page on start.
     *
     * @param url            - url
     * @param resourceBundle - resource bundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
        filterUser.getItems().addAll("Rechte", "User", "Admin");
        this.getData();
        addBtn.setTooltip(new Tooltip("Neuen Benutzer hinzufügen"));
    }

    /**
     * This method is to show the data from the database.
     */
    @FXML
    private void getData() {
        this.allUsers = DB_CALLER_USER.getAllUsers();
        try {
            userTable.getItems().setAll(allUsers);
            userColumn.setCellValueFactory(new PropertyValueFactory<User, String>("UserName"));
            rightColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Right"));
            filterUser.setValue("Rechte");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

// Following are open Popups

    /**
     * This method is to show a selected user in a popup.
     *
     * @throws IOException - IOException
     */
    public void showUser() throws IOException {
        if (Objects.nonNull(userTable.getSelectionModel().getSelectedItem())) {
            openPopup(new UserViewAdminController(userTable.getSelectionModel()
                    .getSelectedItem()), "UserViewAdmin.fxml", true,
                    false);
            this.disableButtons();
        }
    }

    /**
     * This method opens a Popup. With that you can add a new User to the database.
     *
     * @throws IOException - IOException
     */
    public void addUser() throws IOException {
        // TODO: Test method
        openPopup(new UserAddAdminController(), "UserAddAdmin.fxml",
                    true, true);
        this.disableButtons();
    }

    /**
     * With this method you can delete the selected user.
     * It also opens a popup to secure you do not accidental delete a user.
     *
     * @throws IOException - IOException
     */
    public void deleteUser() throws IOException {
        if (Objects.nonNull(userTable.getSelectionModel().getSelectedItem())) {
            openPopup(new NotificationController(userTable.getSelectionModel()
                            .getSelectedItem(), PAGE_NAME), "Notification.fxml",
                    false, false);
            this.disableButtons();
        }
    }

// Following are for functions in this view

    /**
     * This method opens the popup.
     *
     * @param controller      - you define a new controller for the fxml
     *                          page you want to open
     * @param fxmlName        - this fxml page should be opened
     * @param onHidingRefresh - when the calling page should be refreshed
     *                          after closing the popup: true
     * @param onHindingClose  - when the calling page should be closed after closing
     *                          the popup: true
     * @throws IOException - IOException
     */
    void openPopup(Controller controller, String fxmlName, boolean onHidingRefresh, boolean onHindingClose) throws IOException {
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
        stage.initModality(Modality.APPLICATION_MODAL);
        scene.getWindow().sizeToScene();
        stage.show();

    }

    /**
     * This method closes the popup.
     */
    private void closeScene() {
        Stage stClose = (Stage) adminLbl.getScene().getWindow();
        stClose.close();
    }

    /**
     * This method sets the showUser and the delete Button disable as long as no user is selected.
     */
    @FXML
    private void disableButtons() {
        showUserBtn.setDisable(true);
        deleteBtn.setDisable(true);
    }

    /**
     * This method says what to do by selecting a user and double clicking at a user.
     */
    @FXML
    public void clickAction(MouseEvent mouseEvent) {

        if (Objects.nonNull(userTable.getSelectionModel().getSelectedItem())) {
            showUserBtn.setDisable(false);
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

// Following is to Filter and to Search

    /**
     * This method fills the Table depending on filter and search.
     */
    @FXML
    private void setTableContent(ArrayList<User> user) {
        userTable.getItems().setAll(user);
        userColumn.setCellValueFactory(new PropertyValueFactory<User, String>("UserName"));
        rightColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Right"));
    }

    /**
     * This method says what to do when filter and search are set.
     */
    @FXML
    public void setTableWithFilterAndSearch() throws SQLException {
        String selectedUser = filterUser.getSelectionModel().getSelectedItem();
        String searchValue = searchTf.getText();

        if (selectedUser.equals("Rechte") && searchValue.length() != 0) {
             this.setTableContent(getUsersBySearchValue(searchValue));
        } else if (!selectedUser.equals("Rechte")) {
            ArrayList<User> containQuery = new ArrayList<>();
            ArrayList<User> memory = getUsersBySearchValue(searchValue);

            memory.forEach(user3 -> {
                if (user3.getRight().equals(selectedUser)) {
                    containQuery.add(user3);
                }
            });
            this.setTableContent(containQuery);
        } else {
            this.setTableContent(allUsers);
        }
    }


    /**
     * Gets List of Users that contain searchValue in the Username.
     * @param searchValue The value
     * @return the ArrayList of Users containing the searchValue
     */
    private ArrayList<User> getUsersBySearchValue(String searchValue) {
        ArrayList<User> containQuery = new ArrayList<>();

        allUsers.forEach(user1 -> {
            if (user1.getUserName().contains(searchValue)) {
                containQuery.add(user1);
            }
        });

        return containQuery;
    }


    @Override
    public void refresh() {
    }

    public void emptySearchTf(ActionEvent actionEvent) throws SQLException {
        searchTf.setText("");
        this.setTableWithFilterAndSearch();
    }
}
