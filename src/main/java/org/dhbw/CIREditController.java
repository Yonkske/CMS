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
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CIREditController extends MainCirPopupsController {

    @FXML
    public Button cancelBtn;
    @FXML
    public Button submitBtn;

    /**
     * Construktor CIREditController
     *
     * @param selectedCir - Cir Obejekt
     */
    public CIREditController(Cir selectedCir) {
        super(selectedCir);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);
    }

    @FXML
    public void updateCir() {
        try {
            updateStatus();
            loadViewCir();
            //Fixme: Error Handling
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    @FXML
    public void cancel() {
        try {
            loadViewCir();
        }
        //Fixme: Error Handling
        catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     * Update of the CIR over the Cir change method
     *
     * @throws SQLException
     */
    private void updateStatus() throws SQLException {

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
     * @throws IOException  - if fxml file isn't found
     */
    public void loadViewCir() throws SQLException, IOException {
        // loadViewCir
        cir = DB_CALLER_CIR.getCirById(Integer.parseInt(idTf.getText()));
        CIRViewController CIRViewController = new CIRViewController(cir);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CIRView.fxml"));
        loader.setController(CIRViewController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        // Neue View an Fenster anpassen
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
        // Aktuele View schließen
        Stage stClose = (Stage) submitBtn.getScene().getWindow();
        stClose.close();
    }
}
