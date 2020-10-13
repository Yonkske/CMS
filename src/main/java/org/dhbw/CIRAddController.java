package org.dhbw;

import backend.database.DbCallerCir;
import backend.database.DbCallerCit;
import backend.usability.Cir;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class CIRAddController extends Controller implements Initializable {
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
    @FXML public ChoiceBox citChoicebox;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            idTf.setText(String.valueOf(Cir.getCount()+2));

          //todo: Liste von CIT's als String Liste
           // ArrayList<Cit> citListe = new ArrayList<Cit>();
            /*
            citListe = Cit.showAll();
            System.out.println(citListe);
            ArrayList<String> citStringListe = new ArrayList<String>();
           Cit citObejekt;
           for(int i = 0; i <= citListe.size(); i++)
           {
               citObejekt = citListe.get(i);
               citStringListe.add(citObejekt.getCitName());
               System.out.println(citStringListe);
           }
           */
         //todo: Testdaten durch richtige ersetzen
            ArrayList<String> citStringListe = new ArrayList<String>();
            citStringListe.add("1");
            citStringListe.add("Weitere Test Daten, CIT nicht eingebunden");

            citChoicebox.getItems().addAll(citStringListe);
        }
        //Fixme: Error Handling
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        // Button Speichern
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                //Auslesen der Textfelder und speichern in einem String
                String[] sCirArray = new String[10];
                sCirArray[0] = idTf.getText();
                sCirArray[1] = String.valueOf(citChoicebox.getValue());
                sCirArray[2] = nameTf.getText();
                sCirArray[3] = attribut1Tf.getText();
                sCirArray[4] = attribut2Tf.getText();
                sCirArray[5] = attribut3Tf.getText();
                sCirArray[6] = attribut4Tf.getText();
                sCirArray[7] = attribut5Tf.getText();
                sCirArray[8] = attribut6Tf.getText();
                sCirArray[9] = attribut7Tf.getText();
                // String übergeben und neues CIR Obejkt erzeugen
                Cir cirName = Cir.create(sCirArray);

                try {
                    //Fixme: Error Handling
                    // Neues CIR Objekt in die Datenbank schreiben
                    DB_CALLER_CIR.insertCir(cirName);

                } catch (SQLException throwables) {

                    throwables.printStackTrace();

                }
                // Methode Fenster schließen
                fensterSchließen();

            }
        });
        // Abbrechen Button, schließt das Popup
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                    // Methode Fenster schließen
                    //todo: aktuelle ohne Startseite neu laden, --> User/Admin?
                    fensterSchließen();

            }
        });
    }

    /**
     * Methode close Window
     */
    public void fensterSchließen()
    {
        Stage stClose = new Stage();
        stClose = (Stage) submitBtn.getScene().getWindow();
        stClose.close();
    }


}
