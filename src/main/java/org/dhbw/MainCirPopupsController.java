package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public abstract class MainCirPopupsController extends Controller implements Initializable {

    @FXML
    public Label citLbl;
    @FXML
    public Label nameLbl;
    @FXML
    public Label attribut1Lbl;
    @FXML
    public Label attribut2Lbl;
    @FXML
    public Label attribut3Lbl;
    @FXML
    public Label attribut4Lbl;
    @FXML
    public Label attribut5Lbl;
    @FXML
    public Label attribut6Lbl;
    @FXML
    public Label attribut7Lbl;

    @FXML
    public TextField idTf;
    @FXML
    public TextField citTf;
    @FXML
    public TextField nameTf;
    @FXML
    public TextField attribut1Tf;
    @FXML
    public TextField attribut2Tf;
    @FXML
    public TextField attribut3Tf;
    @FXML
    public TextField attribut4Tf;
    @FXML
    public TextField attribut5Tf;
    @FXML
    public TextField attribut6Tf;
    @FXML
    public TextField attribut7Tf;

    Cir cir;

    MainCirPopupsController(Cir cir) {
        this.cir = cir;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Atrribute des Cir Übergeben und in der View ausgeben
        idTf.setText(String.valueOf(cir.getCirID()));
        //todo: Change CIT Int to CIT Type
        citTf.setText(String.valueOf(cir.getCitName()));
        nameTf.setText(cir.getCirName());
        attribut1Tf.setText(cir.getCirAttributes()[0]);
        attribut2Tf.setText(cir.getCirAttributes()[1]);
        attribut3Tf.setText(cir.getCirAttributes()[2]);
        attribut4Tf.setText(cir.getCirAttributes()[3]);
        attribut5Tf.setText(cir.getCirAttributes()[4]);
        attribut6Tf.setText(cir.getCirAttributes()[5]);
        attribut7Tf.setText(cir.getCirAttributes()[6]);

        // Labels für CIT setzen
        Cit cit = cir.getCit();
        attribut1Lbl.setText(cit.getCitAttributes()[1]);
        attribut2Lbl.setText(cit.getCitAttributes()[2]);
        attribut3Lbl.setText(cit.getCitAttributes()[3]);
        attribut4Lbl.setText(cit.getCitAttributes()[4]);
        attribut5Lbl.setText(cit.getCitAttributes()[5]);
        attribut6Lbl.setText(cit.getCitAttributes()[6]);
        attribut7Lbl.setText(cit.getCitAttributes()[7]);

    }
}
