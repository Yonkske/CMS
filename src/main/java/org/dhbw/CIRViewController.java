package org.dhbw;

import backend.usability.Cir;
import javafx.application.Application;
import javafx.application.Platform;
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

    private Cir cir;

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

        // Action Event bearbeiten Button
        editCirBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    openPopUpCirEdit(cir);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        deleteBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    //todo: einbinden der Notification vor dem löschen
                   // openPopUpNotification("Cir Löschen?");
                     deleteSelectedCir(cir);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }

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
        //todo: Jan fragen ?
        scene = new Scene(loadFXML("CIREdit"));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     *  Delete of the Cir over the CirView
     *
     * @param selectedCir - Cir Objekt
     * @throws SQLException
     */
    private void deleteSelectedCir(Cir selectedCir) throws SQLException {
       // openPopUpNotification("Soll das Cir Objekt gelöscht werden?");
        int iSelectedCir = selectedCir.getCirID();
        Cir.delete(iSelectedCir);

    }

    /**
     *
     * @param message
     */
    private void openPopUpNotification(String message)
    {
        //todo: Einbinden sobald NotificationController start vorhanden ist
       // NotificationController.start(message);
    }


    public CIRViewController(Cir cir) {
        this.cir = cir;
    }

}
