package org.dhbw;

import backend.usability.Cit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;


public class CITController extends MainPagesController {
    @FXML
    private Button startpageBtn;
    @FXML
    private Button citBtn;
    @FXML
    private Button statisticBtn;
    @FXML
    private ChoiceBox<Cit> choiceBox;
    @FXML
    private Label idLbl;
    @FXML
    private TextField idTf;
    @FXML
    private Label citLbl;
    @FXML
    private TextField citTf;
    @FXML
    private Label attributeLbl;
    @FXML
    private TextField attribut1Tf;
    @FXML
    private TextField attribut2Tf;
    @FXML
    private TextField attribut3Tf;
    @FXML
    private TextField attribut4Tf;
    @FXML
    private TextField attribut5Tf;
    @FXML
    private TextField attribut6Tf;
    @FXML
    private TextField attribut7Tf;
    @FXML
    private TextField attribut8Tf;
    @FXML
    private Label numberCIRLbl;
    @FXML
    private TextField numberCIRTf;
    @FXML
    private Button deleteBtn;
    @FXML
    private Button citAddBtn;
    @FXML
    private Button userBtn;
    @FXML
    private Label statusLbl;

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
        this.setToolTips();
        if (!Controller.user.getIsAdmin()) {
            deleteBtn.setVisible(false);
            citAddBtn.setVisible(false);
            userBtn.setVisible(false);
        }

        deleteBtn.setDisable(true);

    }
    /**
     * gets Data for the init.
     */
    private void getData() {
        ObservableList<Cit> list = FXCollections.observableArrayList();
        choiceBox.setItems(list);

        try {
            ArrayList<Cit> allCits = DB_CALLER_CIT.getAllCits();
            list.addAll(allCits);
            if (allCits.stream().count() == 0) {
                statusLbl.setText("Noch keine CITs vorhanden!");
                statusLbl.setVisible(true);
            } else {
                statusLbl.setVisible(false);
            }
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
        deleteBtn.setDisable(false);
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
        if (Objects.nonNull(choiceBox.getSelectionModel().getSelectedItem())) {
            openPopup(new NotificationController(choiceBox.getSelectionModel().getSelectedItem(), "CIT"), "Notification.fxml", false);
        }
    }

    /**
     * From the interface, not needed right now-
     */
    @Override
    public void refresh() {
        this.getData();
    }

    /**
     * sets the tooltips;
     */
    private void setToolTips() {
        citAddBtn.setTooltip(new Tooltip("Neuen CI Type hinzufügen"));
        deleteBtn.setTooltip(new Tooltip("Ausgewählten CI Type löschen"));

    }
}
