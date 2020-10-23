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
    Label citLbl;
    @FXML
    Label nameLbl;
    @FXML
    Label attribut1Lbl;
    @FXML
    Label attribut2Lbl;
    @FXML
    Label attribut3Lbl;
    @FXML
    Label attribut4Lbl;
    @FXML
    Label attribut5Lbl;
    @FXML
    Label attribut6Lbl;
    @FXML
    Label attribut7Lbl;

    @FXML
    TextField idTf;
    @FXML
    TextField citTf;
    @FXML
    TextField nameTf;
    @FXML
    TextField attribut1Tf;
    @FXML
    TextField attribut2Tf;
    @FXML
    TextField attribut3Tf;
    @FXML
    TextField attribut4Tf;
    @FXML
    TextField attribut5Tf;
    @FXML
    TextField attribut6Tf;
    @FXML
    TextField attribut7Tf;

    Cir cir;

    MainCirPopupsController(Cir cir) {
        this.cir = cir;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Atrribute des Cir Übergeben und in der View ausgeben
        idTf.setText(String.valueOf(cir.getCirID()));
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
        if(attribut1Lbl.getText().length()>0){
            attribut1Tf.setVisible(true);
        }
        attribut2Lbl.setText(cit.getCitAttributes()[2]);
        if(attribut2Lbl.getText().length()>0){
            attribut2Tf.setVisible(true);
        }
        attribut3Lbl.setText(cit.getCitAttributes()[3]);
        if(attribut3Lbl.getText().length()>0){
            attribut3Tf.setVisible(true);
        }
        attribut4Lbl.setText(cit.getCitAttributes()[4]);
        if(attribut4Lbl.getText().length()>0){
            attribut4Tf.setVisible(true);
        }
        attribut5Lbl.setText(cit.getCitAttributes()[5]);
        if(attribut5Lbl.getText().length()>0){
            attribut5Tf.setVisible(true);
        }
        attribut6Lbl.setText(cit.getCitAttributes()[6]);
        if(attribut6Lbl.getText().length()>0){
            attribut6Tf.setVisible(true);
        }
        attribut7Lbl.setText(cit.getCitAttributes()[7]);
        if(attribut7Lbl.getText().length()>0){
            attribut7Tf.setVisible(true);
        }

    }
}
