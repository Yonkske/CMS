package org.dhbw;

import backend.usability.Cit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class CITAddController extends Controller implements Initializable {
    @FXML
    public Label citLbl;
    @FXML
    public Label meldungLbl;
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
    @FXML
    public Button cancelBtn;
    @FXML
    public Button submitBtn;

    /**
     * Methode from the interface Initializable that auto generates the page on
     * start
     *
     * @param url            - demanded by interface
     * @param resourceBundle - demanded by interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            idTf.setText(String.valueOf(DB_CALLER_CIT.getMaxItemId() + 1));
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Creates a new CIT with the data inserted by the user and inserts it into the database
     *
     * @throws SQLException - on Issue with the database
     */
    @FXML
    public void fillIn() throws SQLException {
        if (Objects.nonNull(citTf.getText())) {
            String[] sCitArray = new String[7];
            int id = Integer.parseInt(idTf.getText());

            String citName = citTf.getText();
            sCitArray[0] = attribut1Tf.getText();
            sCitArray[1] = attribut2Tf.getText();
            sCitArray[2] = attribut3Tf.getText();
            sCitArray[3] = attribut4Tf.getText();
            sCitArray[4] = attribut5Tf.getText();
            sCitArray[5] = attribut6Tf.getText();
            sCitArray[6] = attribut7Tf.getText();
            if (sCitArray[0].length() == 0) {
                showError();
            } else {

                Cit cit = new Cit(id, citName, sCitArray);
                DB_CALLER_CIT.createCit(cit);
                Stage stClose = (Stage) submitBtn.getScene().getWindow();
                stClose.close();
            }
        }
    }


    @FXML
    public void cancelButton(ActionEvent actionEvent) {
        Stage stClose = (Stage) submitBtn.getScene().getWindow();
        stClose.close();
    }

    private void showError() {
        meldungLbl.setVisible(true);
    }
}

