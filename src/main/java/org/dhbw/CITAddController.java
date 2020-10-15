package org.dhbw;

import backend.database.DbCallerCir;
import backend.database.DbCallerCit;
import backend.usability.Cit;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CITAddController extends Controller implements Initializable {

    @FXML public Label idLbl;
    @FXML public Label citLbl;
    @FXML public Label attribut1Lbl;
    @FXML public Label attribut2Lbl;
    @FXML public Label attribut3Lbl;
    @FXML public Label attribut4Lbl;
    @FXML public Label attribut5Lbl;
    @FXML public Label attribut6Lbl;
    @FXML public Label attribut7Lbl;
    @FXML public Label attribut8Lbl;
    @FXML public TextField idTf;
    @FXML public TextField citTf;
    @FXML public TextField attribut1Tf;
    @FXML public TextField attribut2Tf;
    @FXML public TextField attribut3Tf;
    @FXML public TextField attribut4Tf;
    @FXML public TextField attribut5Tf;
    @FXML public TextField attribut6Tf;
    @FXML public TextField attribut7Tf;
    @FXML public TextField attribut8Tf;
    @FXML public Button cancelBtn;
    @FXML public Button submitBtn;




    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            idTf.setText(String.valueOf(DB_CALLER_CIT.getMaxItemId()+1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //Button Speichern
        submitBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.out.println(idTf.getText());
                String[] sCitArray = new String[8];
                int id = Integer.parseInt(idTf.getText());
                System.out.println(id);

                sCitArray[0] = citTf.getText();
                sCitArray[1] = attribut2Tf.getText();
                sCitArray[2] = attribut3Tf.getText();
                sCitArray[3] = attribut4Tf.getText();
                sCitArray[4] = attribut5Tf.getText();
                sCitArray[5] = attribut6Tf.getText();
                sCitArray[6] = attribut7Tf.getText();
                sCitArray[7] = attribut8Tf.getText();

                Cit cit = new Cit(id, sCitArray);
                try {
                    DB_CALLER_CIT.createCit(cit);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
                Stage stClose = new Stage();
                stClose = (Stage) submitBtn.getScene().getWindow();
                stClose.close();


            }
        });
    }
}
