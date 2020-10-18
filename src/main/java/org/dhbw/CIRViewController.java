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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

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
    public void deleteCir(){
        try {
            openPopUpNotification(cir);
        }
        //Fixme: Error Handling
        catch (IOException throwables) {
            throwables.printStackTrace();
        }
    }
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
        CIREditController CIREditController = new CIREditController(selectedCir);
        openPopUp(CIREditController, "CIREdit.fxml");
    }

    /**
     * Opens the popup to delete the selected cir
     *
     * @param selectedCir - selected CIR
     * @throws IOException - if fxml file isn't found
     */
    private void openPopUpNotification(Cir selectedCir) throws IOException {
        NotificationController notificationController = new NotificationController(selectedCir, "Startpage");
        openPopUp(notificationController, "Notification.fxml");
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
