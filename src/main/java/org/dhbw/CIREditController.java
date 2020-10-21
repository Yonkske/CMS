package org.dhbw;

import backend.usability.Cir;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;


public class CIREditController extends MainCirPopupsController {

    @FXML
    private Button cancelBtn;
    @FXML
    private Button submitBtn;
    @FXML
    private Label meldungLbl;

    /**
     * Construktor CIREditController
     *
     * @param selectedCir - Cir Obejekt
     */
    public CIREditController(Cir selectedCir) {
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
     * updates an cir
     */
    @FXML
    public void updateCir() {
        if (checkForRegex(nameTf.getText()) && nameTf.getText().length() > 0) {
            if (updateStatus()) {
                closeStage();
            } else {
                showError("Maximal 255 Zeichen!");
            }
        } else {
            showError("Name darf nicht leer sein!");
        }
    }

    /**
     * cancles the dialog.
     */
    @FXML
    public void cancel() {
        closeStage();
    }

    /**
     * Update of the CIR over the Cir change method
     */
    private boolean updateStatus() {
        String[] newAttributes = new String[7];

        newAttributes[0] = attribut1Tf.getText();
        newAttributes[1] = attribut2Tf.getText();
        newAttributes[2] = attribut3Tf.getText();
        newAttributes[3] = attribut4Tf.getText();
        newAttributes[4] = attribut5Tf.getText();
        newAttributes[5] = attribut6Tf.getText();
        newAttributes[6] = attribut7Tf.getText();

        cir.setName(nameTf.getText());
        cir.setAttributes(newAttributes);

        return DB_CALLER_CIR.updateCir(cir);
    }

    /**
     * Closes the stage
     */
    private void closeStage() {
        Stage stClose = (Stage) submitBtn.getScene().getWindow();
        stClose.close();
    }

    /**
     * Displays the error message
     *
     * @param errorMessage String - error message
     */
    private void showError(String errorMessage) {
        meldungLbl.setText(errorMessage);
        meldungLbl.setVisible(true);
    }
}
