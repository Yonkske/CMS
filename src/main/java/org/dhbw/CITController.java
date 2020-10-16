package org.dhbw;

import backend.database.DbCallerCir;
import backend.usability.Cir;
import backend.usability.Cit;
import backend.usability.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;
import java.sql.SQLException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;



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


    private final String PAGE_NAME = "CIT";

    private Cit cit;

    public void CITController(Cit cit) {
        this.cit = cit;
    }

    public void initialize() throws SQLException {
        ObservableList<Cit> list = FXCollections.observableArrayList();
        choiceBox.setItems(list);
        list.addAll(DB_CALLER_CIT.getAllCits());

        super.user = new User("foobar", "foobar", false, false, "Ion", "Tabyrca");


        if (!super.user.getIsAdmin()) {
            //adminLbl.setText(super.user.getSurName() + ", " + super.user.getName());
            deleteBtn.setVisible(false);
            citaddBtn.setVisible(false);
            userBtn.setVisible(false);
        } else {
            //adminLbl.setText(super.user.getSurName() + ", " + super.user.getName() + " (Admin)");
        }

    }

    public void swapToCITAdd(ActionEvent actionEvent) throws IOException {
        CITAddController citAddController = new CITAddController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CITAdd.fxml"));
        loader.setController(citAddController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
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
        NotificationController notificationController = new NotificationController(choiceBox.getSelectionModel().getSelectedItem(), PAGE_NAME);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notification.fxml"));
        loader.setController(notificationController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }


    @Override
    public void refresh() {

    }
}
