package org.dhbw;

import backend.usability.Cit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Objects;
import java.util.ResourceBundle;


public class CITController extends MainPagesController {
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

    /**
     * Methode from the interface Initializable that auto generates the page on
     * start
     *
     * @param url            - demanded by interface
     * @param resourceBundle - demanded by interface
     */
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        this.getData();

        if (!Controller.user.getIsAdmin()) {
            deleteBtn.setVisible(false);
            citaddBtn.setVisible(false);
            userBtn.setVisible(false);
        }

    }

    private void getData() {
        ObservableList<Cit> list = FXCollections.observableArrayList();
        choiceBox.setItems(list);

        try {
            list.addAll(DB_CALLER_CIT.getAllCits());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    /**
     * Opens the citAddd popup on button click
     *
     * @throws IOException - if fxml file isn't found
     */
    public void swapToCITAdd() throws IOException {
        openPopup(new CITAddController(), "CITAdd.fxml", true);
    }

    /**
     * Fills the text fields with the values of the selected cit
     *
     * @throws SQLException - if fxml file isn't found
     */
    public void fillingIn() throws SQLException {
        Cit cit = choiceBox.getSelectionModel().getSelectedItem();

        // Avoids an exception when no CIT was selected in the choiceBox before a new CIT is created
        if (Objects.nonNull(cit)) {
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

        if (attribut1Tf.getText()=="") {
            attribut1Tf.setVisible(false);
        }
        else {
            attribut1Tf.setVisible(true);
        }
        if (attribut2Tf.getText()=="") {
            attribut2Tf.setVisible(false);
        }
        else {
            attribut2Tf.setVisible(true);
        }
        if (attribut3Tf.getText()=="") {
            attribut3Tf.setVisible(false);
        }
        else {
            attribut3Tf.setVisible(true);
        }
        if (attribut4Tf.getText()=="") {
            attribut4Tf.setVisible(false);
        }
        else {
            attribut4Tf.setVisible(true);
        }
        if (attribut5Tf.getText()=="") {
            attribut5Tf.setVisible(false);
        }
        else {
            attribut5Tf.setVisible(true);
        }
        if (attribut6Tf.getText()=="") {
            attribut6Tf.setVisible(false);
        }
        else {
            attribut6Tf.setVisible(true);
        }
        if (attribut7Tf.getText()=="") {
            attribut7Tf.setVisible(false);
        }
        else {
            attribut7Tf.setVisible(true);
        }
        if (attribut8Tf.getText()=="") {
            attribut8Tf.setVisible(false);
        }
        else {
            attribut8Tf.setVisible(true);
        }

    }

    /**
     * Opens the popup to delete the selected cit
     */
    @FXML
    public void openDeleteCitPopup() throws IOException {
        // FIXME
        String PAGE_NAME = "CIT";
        if (Objects.nonNull(choiceBox.getSelectionModel().getSelectedItem())) {
            openPopup(new NotificationController(choiceBox.getSelectionModel().getSelectedItem(), PAGE_NAME), "Notification.fxml", false);
        }
    }


    @Override
    public void refresh() {
        this.getData();
    }
}
