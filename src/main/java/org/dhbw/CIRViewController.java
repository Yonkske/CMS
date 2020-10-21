package org.dhbw;

import backend.usability.Cir;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class CIRViewController extends MainCirPopupsController {
    @FXML
    public Button deleteBtn;
    @FXML
    public Button editCirBtn;
    @FXML
    public Button cancelBtn;

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
        //Fixme: Error Handling
            catch (IOException e) {
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
        //Fixme: Error Handling
        catch (IOException throwables) {
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
     * open the Cir Edit View
     *
     * @param selectedCir - selected CIR
     * @throws IOException - if fxml file isn't found
     */
    private void openPopUpCirEdit(Cir selectedCir) throws IOException {
        openPopUp(new CIREditController(selectedCir), "CIREdit.fxml");
    }

    /**
     * Opens the popup to delete the selected cir
     *
     * @param selectedCir - selected CIR
     * @throws IOException - if fxml file isn't found
     */
    private void openPopUpNotification(Cir selectedCir) throws IOException {
        openPopUp(new NotificationController(selectedCir, "Startpage"), "Notification.fxml");
    }

    private void openPopUp(Controller controller, String fxmlName) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();

    }

}
