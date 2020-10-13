package org.dhbw;

import backend.usability.Cir;
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


public class CIREditController extends Controller implements Initializable {
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

    @FXML public Button cancelBtn;
    @FXML public Button submitBtn;
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

        // Button Speichern
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // Cir Ändern in der Datenbank
                    updateStatus();
                    // CIR View wird geladen und CirEdit wird geschlossen
                    loadViewCir();
                } catch (SQLException | IOException throwables) {
                    throwables.printStackTrace();
                }

            }
        });

        // Button Abbrechen
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                try {
                    // CIR View wird geladen und CirEdit wird geschlossen
                    loadViewCir();
                 }
                catch (SQLException | IOException throwables) {
                     throwables.printStackTrace();
                 }
            }
        });

    }

    /**
     * Update of the CIR over the Cir change method
     *
     * @throws SQLException
     */
    private void  updateStatus() throws SQLException {

        // Attribute an String Array größe 10 Übergeben
        String[] sAttributeCir = new String[10];
        //todo: Integer in CIT Type ändern
        int iCirId = Integer.parseInt(idTf.getText());

        sAttributeCir[0] = idTf.getText();
        sAttributeCir[1] = citTf.getText();
        sAttributeCir[2] = nameTf.getText();
        sAttributeCir[3] = attribut1Tf.getText();
        sAttributeCir[4] = attribut2Tf.getText();
        sAttributeCir[5] = attribut3Tf.getText();
        sAttributeCir[6] = attribut4Tf.getText();
        sAttributeCir[7] = attribut5Tf.getText();
        sAttributeCir[8] = attribut6Tf.getText();
        sAttributeCir[9] = attribut7Tf.getText();

        Cir.change(sAttributeCir, iCirId);
    }

    /**
     * load of the ControllerCirView
     *
     * @throws SQLException
     * @throws IOException
     */
    public void loadViewCir() throws SQLException, IOException {
        // loadViewCir
        cir = Cir.showCir(Integer.parseInt(idTf.getText()));
        CIRViewController CIRViewController = new CIRViewController(cir);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CIRView.fxml"));
        loader.setController(CIRViewController);
        Parent root = loader.load();
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);
        // Neue View an Fenster anpassen
        stage1.setScene(scene);
        scene.getWindow().sizeToScene();
        stage1.show();
        // Aktuele View schließen
        Stage stClose = new Stage();
        stClose = (Stage) submitBtn.getScene().getWindow();
        stClose.close();
    }

    /**
     *  Konstruktor CIREditController
     *
     * @param selectedCir - Cir Obejekt
     */
    public CIREditController(Cir selectedCir){
        this.cir = selectedCir;
    }
}
