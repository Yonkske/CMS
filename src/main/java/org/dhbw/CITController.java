package org.dhbw;

import backend.usability.Cit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;



public class CITController extends MainPagesController {
    @FXML
    public Label adminLbl;
    @FXML
    public Button startpageBtn;
    @FXML
    public Button citBtn;
    @FXML
    public Button statisticBtn;
    @FXML
    public ChoiceBox<Cit> choiceBox;
    @FXML
    public Label idLbl;
    @FXML
    public TextField idTf;
    @FXML
    public Label citLbl;
    @FXML
    public TextField citTf;
    @FXML
    public Label attributeLbl;
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
    public TextField attribut8Tf;
    @FXML
    public Label numberCIRLbl;
    @FXML
    public TextField numberCIRTf;
    @FXML
    public Button deleteBtn;
    @FXML
    public Button citaddBtn;
    @FXML
    public Button userBtn;


    public void initialize(URL url, ResourceBundle resourceBundle)  {
        super.initialize(url, resourceBundle);

        ObservableList<Cit> list = FXCollections.observableArrayList();
        choiceBox.setItems(list);

        try {
            list.addAll(DB_CALLER_CIT.getAllCits());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

        if (!Controller.user.getIsAdmin()) {
            deleteBtn.setVisible(false);
            citaddBtn.setVisible(false);
            userBtn.setVisible(false);
        }

    }

    public void swapToCITAdd(ActionEvent actionEvent) throws IOException {
        openPopup(new CITAddController(), "CITAdd.fxml", false);
    }

    public void fillingIn(ActionEvent actionEvent) throws IOException, SQLException {
        Cit cit = choiceBox.getSelectionModel().getSelectedItem();


        idTf.setText(String.valueOf(cit.getCitID()));
        citTf.setText(cit.getCitName());

        attribut1Tf.setText(cit.getCitAttributes()[0]);
        attribut2Tf.setText(cit.getCitAttributes()[1]);
        attribut3Tf.setText(cit.getCitAttributes()[2]);
        attribut4Tf.setText(cit.getCitAttributes()[3]);
        attribut5Tf.setText(cit.getCitAttributes()[4]);
        attribut6Tf.setText(cit.getCitAttributes()[5]);
        attribut7Tf.setText(cit.getCitAttributes()[6]);
        attribut8Tf.setText(cit.getCitAttributes()[7]);
        numberCIRTf.setText(String.valueOf(DB_CALLER_CIR.getCirCountForType(cit)));


    }

    /**
     * Opens the popup to delete the selected cit
     */
    @FXML
    public void openDeleteCitPopup() throws IOException {
        // FIXME
        String PAGE_NAME = "CIT";
        openPopup(new NotificationController(choiceBox.getSelectionModel().getSelectedItem(), PAGE_NAME), "Notification.fxml", false);
    }


    @Override
    public void refresh() {

    }
}
