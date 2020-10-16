package org.dhbw;


import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public abstract class MainPagesController extends Controller implements IRefreshable{

    @FXML
    Button benutzerDropDownBtn;
    @FXML
    Button passwordEditBtn;
    @FXML
    Button logoutBtn;

    public void openUserInfo() throws IOException {
        openPopup(new UserInfoController(), "UserInfo.fxml", false);
    }

    public void openChangePassword() throws IOException {
        openPopup(new ChangePasswordController(), "ChangePassword.fxml", false);
    }

    public void swapToLogin() {

    }

    /**
     * Opens a popup window
     *
     * @param controller controller for the popup - must match fxmlName
     * @param fxmlName   of the fxml file to be opened in the popup - must match controller
     * @param onHiding   if the page should be refreshed on closing the popup
     * @throws IOException
     */
    void openPopup(Controller controller, String fxmlName, boolean onHiding) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
        //loader.setController(controller);
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
