package org.dhbw;

import backend.usability.Cir;
import javafx.application.Application;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    /**
     *  start of the CIR View
     *
     * @param stage - Stage
     * @param selectedCir - Cir Objekt
     * @throws IOException
     */
    public void start(Stage stage, Cir selectedCir) throws IOException {
        scene = new Scene(loadFXML("CIRView"));
        stage.setScene(scene);
        // Setzen der Attribut Werte
        idTf.setText(String.valueOf(selectedCir.getCirID()));
        //todo: Change CIT Int to CIT Type
        citTf.setText(String.valueOf(selectedCir.getCitID()));
        nameTf.setText(selectedCir.getCirName());
        attribut1Tf.setText(selectedCir.getCirAttributes()[0]);
        attribut2Tf.setText(selectedCir.getCirAttributes()[1]);
        attribut3Tf.setText(selectedCir.getCirAttributes()[2]);
        attribut4Tf.setText(selectedCir.getCirAttributes()[3]);
        attribut5Tf.setText(selectedCir.getCirAttributes()[4]);
        attribut6Tf.setText(selectedCir.getCirAttributes()[5]);
        attribut7Tf.setText(selectedCir.getCirAttributes()[6]);

        editCirBtn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    Cir selectedCir = Cir.showCir(Integer.parseInt(idTf.getText()));
                    openPopUpCirEdit(selectedCir);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        stage.show();
    }

    /**
     *  start of cirView, load the FXML
     *
     * @param cirView - String name of the cirView
     * @return - The CirEdit View
     * @throws IOException
     */
    private Parent loadFXML(String cirView) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(cirView + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     *   open the Cir Edit View
     *
     * @param selectedCir - Cir Objekt
     * @throws IOException
     */
    private void openPopUpCirEdit(Cir selectedCir) throws IOException {
        //todo: Jan fragen ?
        FXMLFactory.setRoot("CIREdit");

    }

    /**
     *  Delete of the Cir over the CirView
     *
     * @param selectedCir - Cir Objekt
     * @throws SQLException
     */
    private void deleteSelectedCir(Cir selectedCir) throws SQLException {
        openPopUpNotification("Soll das Cir Objekt gelöscht werden?");
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



}
