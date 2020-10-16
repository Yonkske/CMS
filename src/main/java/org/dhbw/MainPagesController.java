package org.dhbw;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public abstract class MainPagesController extends Controller implements IRefreshable, Initializable {

    @FXML
    MenuButton adminUserMB;
    @FXML
    MenuItem logOutMbItem;
    @FXML
    Button benutzerDropDownBtn;
    @FXML
    Button passwordEditBtn;
    @FXML
    Button logoutBtn;


    public void initialize(URL url, ResourceBundle resourceBundle) {
        adminUserMB.setText(Controller.user.getSurName() + ", " + Controller.user.getName());
    }

    @FXML
    public void logout() throws IOException {
        FXMLFactory.setRoot("Login");
    }

    /**
     * Opens the user info on button click
     *
     * @throws IOException
     */
    public void openUserInfo() throws IOException {
        openPopup(null, "UserInfo.fxml", false);
    }

    /**
     * Opens change password on button click
     *
     * @throws IOException
     */
    public void openChangePassword() throws IOException {
        openPopup(null, "ChangePassword.fxml", false);
    }

    /**
     * Logs the current user out and swaps the scene back to the login page
     */
    public void logOut() throws IOException {
        Controller.user = null;
        FXMLFactory.setRoot("Login");
    }

    /**
     * Swaps the scene to the startpage on button click
     *
     * @throws IOException
     */
    @FXML
    public void swapToStartpage() throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

    /**
     * Swaps the scene to the CITPage on button click
     *
     * @throws IOException
     */
    @FXML
    public void swapToCit() throws IOException {
        FXMLFactory.setRoot("CIT");
    }

    /**
     * Swaps the scene to the statistic page on button click
     *
     * @throws IOException
     */
    @FXML
    public void swapToStatistic() throws IOException {
        FXMLFactory.setRoot("Statistic");
    }

    /**
     * Swaps the scene to the user page on button click
     *
     * @throws IOException
     */
    @FXML
    public void swapToUserAdmin() throws IOException {
        FXMLFactory.setRoot("UserAdmin");
    }

    /**
     * Opens a popup window
     *
     * @param controller controller for the popup - must match fxmlName or be null
     * @param fxmlName   of the fxml file to be opened in the popup - must match controller
     * @param onHiding   if the page should be refreshed on closing the popup
     * @throws IOException
     */
    void openPopup(Controller controller, String fxmlName, boolean onHiding) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
        if(Objects.nonNull(controller)){
            loader.setController(controller);
        }
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setResizable(false);
        if (onHiding) {
            stage.setOnHiding(windowEvent -> refresh());
        }
        stage.getIcons().add(new Image(App.class.getResourceAsStream("icons/favicon1.jpg")));
        stage.setTitle("CMS - Configuration Management System");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

}
