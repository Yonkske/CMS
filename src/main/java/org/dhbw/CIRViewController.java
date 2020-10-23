package org.dhbw;

import backend.usability.Cir;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CIRViewController extends MainCirPopupsController {
    @FXML
    private Button deleteBtn;
    @FXML
    private Button editCirBtn;
    @FXML
    private Button cancelBtn;

    /**
     * Contrktor of the Method CIRViewController
     *
     * @param selectedCir - CIR Objekt
     */
    public CIRViewController(Cir selectedCir) {
        super(selectedCir);
    }

    /**
     * Initializes the Object.
     * @param url an url
     * @param resourceBundle an resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    /**
     *  opens the Edit CIR dialog
     */
    @FXML
    public void editCir(){
        try {
            openPopUpCirEdit(cir);
            Stage stClose = (Stage) editCirBtn.getScene().getWindow();
            stClose.close();

        }
            catch (IOException e) {
            // only happens if the resources couldn't be loaded -> won't happen
            e.printStackTrace();
        }
    }

    /**
     * opens the delete CIR dialog
     */
    public void deleteCir(){
        try {
            openPopUpNotification(cir);
        }
        catch (IOException throwables) {
            // only happens if the resources couldn't be loaded -> won't happen
            throwables.printStackTrace();
        }
    }

    /**
     * cancels the stage
     */
    public void cancel(){
        Stage stClose = (Stage) cancelBtn.getScene().getWindow();
        stClose.close();
    }

    /**
     * opens the edit dialog
     */
    /**
     * open the Cir Edit View
     *
     * @param selectedCir - selected CIR
     * @throws IOException - if fxml file isn't found
     */
    private void openPopUpCirEdit(Cir selectedCir) throws IOException {
        openPopUp(new CIREditController(selectedCir), "CIREdit.fxml");
    }

    /**
     * opens a notification
     */
    /**
     * Opens the popup to delete the selected cir
     *
     * @param selectedCir - selected CIR
     * @throws IOException - if fxml file isn't found
     */
    private void openPopUpNotification(Cir selectedCir) throws IOException {
        openPopUp(new NotificationController(selectedCir, "Startpage"), "Notification.fxml");
    }

    /**
     * Opens a Popup of a Controller.
     * @param controller The controller class.
     * @param fxmlName the fxml name.
     * @throws IOException - an exception
     */
    private void openPopUp(Controller controller, String fxmlName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();

    }

}
