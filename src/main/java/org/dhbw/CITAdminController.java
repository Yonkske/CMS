package org.dhbw;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CITAdminController extends Controller {
    @FXML public Label adminLbl;
    @FXML public Button startpageBtn;
    @FXML public Button citBtn;
    @FXML public Button statisticBtn;
    @FXML public ChoiceBox choiceBox;
    @FXML public Button searchBtn;
    @FXML public Label idLbl;
    @FXML public TextField idTf;
    @FXML public Label citLbl;
    @FXML public TextField citTf;
    @FXML public Label attributeLbl;
    @FXML public TextField attribut1Tf;
    @FXML public TextField attribut2Tf;
    @FXML public TextField attribut3Tf;
    @FXML public TextField attribut4Tf;
    @FXML public TextField attribut5Tf;
    @FXML public TextField attribut6Tf;
    @FXML public TextField attribut7Tf;
    @FXML public TextField attribut8Tf;
    @FXML public Label numberCIRLbl;
    @FXML public TextField numberCIRLTf;
    @FXML public Button deleteBtn;
    @FXML public Button citaddBtn;

    public void initialize() throws SQLException {

        ArrayList<String> citStringListe = new ArrayList<String>();
        citStringListe.add("1");
        citStringListe.add("Weitere Test Daten, CIT nicht eingebunden");

        choiceBox.getItems().addAll(citStringListe);
    }

    public void swapToStartpageAdmin(ActionEvent actionEvent) throws IOException {
       // FXMLFactory.setRoot("StartpageAdmin.fxml");
        // Laden der neuen Scene Startseite
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StartpageAdmin.fxml"));
        Parent root = loader.load();
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);

        stage1.setScene(scene);
        scene.getWindow().sizeToScene();
        stage1.show();
        // schließen der aktuellen view
        Stage stClose = new Stage();
        stClose = (Stage) startpageBtn.getScene().getWindow();
        stClose.close();

    }

    public void swapToCITAdmin(ActionEvent actionEvent) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CITAdmin.fxml"));
        Parent root = loader.load();
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);

        stage1.setScene(scene);
        scene.getWindow().sizeToScene();
        stage1.show();
        // schließen der aktuellen view
        Stage stClose = new Stage();
        stClose = (Stage) startpageBtn.getScene().getWindow();
        stClose.close();
    }

    public void swapToStatisticAdmin(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("StatisticAdmin"));
        Parent root = loader.load();
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);

        stage1.setScene(scene);
        scene.getWindow().sizeToScene();
        stage1.show();
        // schließen der aktuellen view
        Stage stClose = new Stage();
        stClose = (Stage) startpageBtn.getScene().getWindow();
        stClose.close();
    }

    public void swapToCITAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CITAdd"));
        Parent root = loader.load();
        Stage stage1 = new Stage();
        Scene scene = new Scene(root);

        stage1.setScene(scene);
        scene.getWindow().sizeToScene();
        stage1.show();
        // schließen der aktuellen view
        Stage stClose = new Stage();
        stClose = (Stage) startpageBtn.getScene().getWindow();
        stClose.close();
    }



}
