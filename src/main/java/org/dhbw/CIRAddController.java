package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;


public class CIRAddController extends Controller implements Initializable {
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
    public Label meldungLbl;

    @FXML
    public TextField idTf;
    @FXML
    public ChoiceBox<Cit> citChoicebox;
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
        if(citChoicebox.getSelectionModel().getSelectedItem() == null ){
            meldungLbl.setVisible(true);
        }
        else{
        Cit cit = citChoicebox.getSelectionModel().getSelectedItem();
        String[] sCirArray = new String[10];
        sCirArray[0] = idTf.getText();
        sCirArray[1] = String.valueOf(cit.getCitID());
        sCirArray[2] = nameTf.getText();
        sCirArray[3] = attribut1Tf.getText();
        sCirArray[4] = attribut2Tf.getText();
        sCirArray[5] = attribut3Tf.getText();
        sCirArray[6] = attribut4Tf.getText();
        sCirArray[7] = attribut5Tf.getText();
        sCirArray[8] = attribut6Tf.getText();
        sCirArray[9] = attribut7Tf.getText();
        Cir cirName = Cir.create(sCirArray);

        try {
            //Fixme: Error Handling
            DB_CALLER_CIR.insertCir(cirName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            CIRViewController CIRViewController = new CIRViewController(cirName);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CIRView.fxml"));
            loader.setController(CIRViewController);
            Parent root = loader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            scene.getWindow().sizeToScene();
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeWindow();
    }}

    /**
     * closes the popup on button click
     */
    @FXML
    public void cancel() {
        closeWindow();
    }

    /**
     * Changes the text of the labels depending on the selection in the choiceBox
     */
    @FXML
    public void setLabelTexts() {
        Cit cit = citChoicebox.getSelectionModel().getSelectedItem();
        attribut1Lbl.setText(cit.getCitAttributes()[1]);
        attribut2Lbl.setText(cit.getCitAttributes()[2]);
        attribut3Lbl.setText(cit.getCitAttributes()[3]);
        attribut4Lbl.setText(cit.getCitAttributes()[4]);
        attribut5Lbl.setText(cit.getCitAttributes()[5]);
        attribut6Lbl.setText(cit.getCitAttributes()[6]);
        attribut7Lbl.setText(cit.getCitAttributes()[7]);
    }


    /**
     * Methode close Window
     */
    public void closeWindow() {
        Stage stClose = (Stage) submitBtn.getScene().getWindow();
        stClose.close();
    }


}
