package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CIRAddController extends Controller implements Initializable {
    @FXML
    private Label citLbl;
    @FXML
    private Label nameLbl;
    @FXML
    private Label attribut1Lbl;
    @FXML
    private Label attribut2Lbl;
    @FXML
    private Label attribut3Lbl;
    @FXML
    private Label attribut4Lbl;
    @FXML
    private Label attribut5Lbl;
    @FXML
    private Label attribut6Lbl;
    @FXML
    private Label attribut7Lbl;
    @FXML
    private Label meldungLbl;

    @FXML
    private TextField idTf;
    @FXML
    private ChoiceBox<Cit> citChoicebox;
    @FXML
    private TextField nameTf;
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
    private Button cancelBtn;
    @FXML
    private Button submitBtn;

    /**
     * Initializes the Object.
     *
     * @param url            an url
     * @param resourceBundle an resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            idTf.setText(String.valueOf(DB_CALLER_CIR.getMaxItemId() + 1));
            ObservableList<Cit> list = FXCollections.observableArrayList();
            citChoicebox.setItems(list);
            list.addAll(DB_CALLER_CIT.getAllCits());
        }
        //Fixme: Error Handling
        catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /**
     * Creates a new CIR with the input values of the text fields
     */
    @FXML
    public void fillIn() {
        if (nameTf.getText().length() != 0 && !nameTf.getText().matches("^[\\s]+$")) {


            if (citChoicebox.getSelectionModel().getSelectedItem() == null) {
                showError("CIT darf nicht leer sein!");
            } else {

                String[] attributes = new String[7];
                attributes[0] = attribut1Tf.getText();
                attributes[1] = attribut2Tf.getText();
                attributes[2] = attribut3Tf.getText();
                attributes[3] = attribut4Tf.getText();
                attributes[4] = attribut5Tf.getText();
                attributes[5] = attribut6Tf.getText();
                attributes[6] = attribut7Tf.getText();

                Cir cirToInsert = new Cir(Integer.parseInt(idTf.getText()), citChoicebox.getSelectionModel().getSelectedItem(), nameTf.getText(), attributes);

                if (DB_CALLER_CIR.insertCir(cirToInsert)) {
                    closeWindow();
                } else {
                    showError("Maximal 255 Zeichen!");
                }
            }
        }
    }

    /**
     * closes the popup on button click
     */
    @FXML
    public void cancel() {
        closeWindow();
    }

    private void showError(String errorMessage) {
        meldungLbl.setText(errorMessage);
        meldungLbl.setVisible(true);
    }

    /**
     * Changes the text of the labels depending on the selection in the choiceBox
     */
    @FXML
    public void setLabelTexts() {
        Cit cit = citChoicebox.getSelectionModel().getSelectedItem();
        attribut1Lbl.setText(cit.getCitAttributes()[1]);
        if (attribut1Lbl.getText().length() > 0) {
            attribut1Lbl.setVisible(true);
            attribut1Tf.setVisible(true);
        } else {
            attribut1Tf.setVisible(false);
        }
        attribut2Lbl.setText(cit.getCitAttributes()[2]);
        if (attribut2Lbl.getText().length() > 0) {
            attribut2Lbl.setVisible(true);
            attribut2Tf.setVisible(true);
        } else {
            attribut2Tf.setVisible(false);
        }
        attribut3Lbl.setText(cit.getCitAttributes()[3]);
        if (attribut3Lbl.getText().length() > 0) {
            attribut3Lbl.setVisible(true);
            attribut3Tf.setVisible(true);
        } else {
            attribut3Tf.setVisible(false);
        }
        attribut4Lbl.setText(cit.getCitAttributes()[4]);
        if (attribut4Lbl.getText().length() > 0) {
            attribut4Lbl.setVisible(true);
            attribut4Tf.setVisible(true);
        } else {
            attribut4Tf.setVisible(false);
        }
        attribut5Lbl.setText(cit.getCitAttributes()[5]);
        if (attribut5Lbl.getText().length() > 0) {
            attribut5Lbl.setVisible(true);
            attribut5Tf.setVisible(true);
        } else {
            attribut5Tf.setVisible(false);
        }
        attribut6Lbl.setText(cit.getCitAttributes()[6]);
        if (attribut6Lbl.getText().length() > 0) {
            attribut6Lbl.setVisible(true);
            attribut6Tf.setVisible(true);
        } else {
            attribut6Tf.setVisible(false);
        }
        attribut7Lbl.setText(cit.getCitAttributes()[7]);
        if (attribut7Lbl.getText().length() > 0) {
            attribut7Lbl.setVisible(true);
            attribut7Tf.setVisible(true);
        } else {
            attribut7Tf.setVisible(false);
        }
    }


    /**
     * Methode close Window
     */
    public void closeWindow() {
        Stage stClose = (Stage) submitBtn.getScene().getWindow();
        stClose.close();
    }


}
