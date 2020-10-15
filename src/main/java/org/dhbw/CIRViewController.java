package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CIRViewController extends Controller implements Initializable  {
    private static Scene scene;

    @FXML public Label idLbl;
    @FXML public Label citLbl;
    @FXML public Label nameLbl;
    @FXML public Label attribut1Lbl;
    @FXML public Label attribut2Lbl;
    @FXML public Label attribut3Lbl;
    @FXML public Label attribut4Lbl;
    @FXML public Label attribut5Lbl;
    @FXML public Label attribut6Lbl;
    @FXML public Label attribut7Lbl;

    @FXML public TextField idTf;
    @FXML public TextField citTf;
    @FXML public TextField nameTf;
    @FXML public TextField attribut1Tf;
    @FXML public TextField attribut2Tf;
    @FXML public TextField attribut3Tf;
    @FXML public TextField attribut4Tf;
    @FXML public TextField attribut5Tf;
    @FXML public TextField attribut6Tf;
    @FXML public TextField attribut7Tf;
    @FXML public Button deleteBtn;
    @FXML public Button editCirBtn;
    @FXML public Button cancelBtn;

    private Cir cir;
    private final String PAGE_NAME = "Startpage";

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        // Atrribute des Cir Übergeben und in der View ausgeben
        idTf.setText(String.valueOf(cir.getCirID()));
        //todo: Change CIT Int to CIT Type
        citTf.setText(String.valueOf(cir.getCitID()));
        nameTf.setText(cir.getCirName());
        attribut1Tf.setText(cir.getCirAttributes()[0]);
        attribut2Tf.setText(cir.getCirAttributes()[1]);
        attribut3Tf.setText(cir.getCirAttributes()[2]);
        attribut4Tf.setText(cir.getCirAttributes()[3]);
        attribut5Tf.setText(cir.getCirAttributes()[4]);
        attribut6Tf.setText(cir.getCirAttributes()[5]);
        attribut7Tf.setText(cir.getCirAttributes()[6]);


        // Cit Labels setzen
        Cit cit = cir.getCit();
        attribut1Lbl.setText(cit.getCitAttributes()[1]);
        attribut2Lbl.setText(cit.getCitAttributes()[2]);
        attribut3Lbl.setText(cit.getCitAttributes()[3]);
        attribut4Lbl.setText(cit.getCitAttributes()[4]);
        attribut5Lbl.setText(cit.getCitAttributes()[5]);
        attribut6Lbl.setText(cit.getCitAttributes()[6]);
        attribut7Lbl.setText(cit.getCitAttributes()[7]);

        // Action Event bearbeiten Button
        editCirBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // Aufrufen von View CIR Edit
                    openPopUpCirEdit(cir);
                    // Aktuelle View schließen
                    Stage stClose = new Stage();
                    stClose = (Stage) editCirBtn.getScene().getWindow();
                    stClose.close();

                }
                //Fixme: Error Handling
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    //todo: einbinden der Notification vor dem löschen
                    openPopUpNotification(cir);
                    //deleteSelectedCir(cir);
                }
                //Fixme: Error Handling
                catch (IOException throwables) {
                    throwables.printStackTrace();
                }

            }
        });
        // Abbrechen Button, schließt das Popup
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Stage stClose = new Stage();
                stClose = (Stage) cancelBtn.getScene().getWindow();
                stClose.close();

            }
        });
    }

    /**
     *   open the Cir Edit View
     *
     * @param selectedCir - Cir Objekt
     * @throws IOException
     */
    private void openPopUpCirEdit(Cir selectedCir) throws IOException {
        // Aufruf View CIREdit
        CIREditController CIREditController = new CIREditController(selectedCir);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CIREdit.fxml"));
        loader.setController(CIREditController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        // View an Fenster anpassen
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();


    }

    /**
     * Opens the popup to delete the selected cir
     *
     * @param selectedCir
     * @throws IOException
     */
    private void openPopUpNotification(Cir selectedCir) throws IOException {
        NotificationController notificationController = new NotificationController(selectedCir, PAGE_NAME);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notification.fxml"));
        loader.setController(notificationController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    /**
     * Kontrktor of the Method CIRViewController
     *
     * @param cir - CIR Objekt
     */
    public CIRViewController(Cir cir) {
        this.cir = cir;
    }

}
