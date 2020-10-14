package org.dhbw;

import backend.database.DbCallerCit;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class CITAdmin extends Controller {
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

        DbCallerCit dbc = new DbCallerCit();
        ArrayList<String> citStringListe = new ArrayList<String>();
        citStringListe.add("1");
        citStringListe.add("Weitere Test Daten, CIT nicht eingebunden");
        ArrayList<String> citNameListe = new ArrayList<String>();
        choiceBox.getItems().addAll(citStringListe);
    }

    public void swapToStartpageAdmin(ActionEvent actionEvent) throws IOException {
       // FXMLFactory.setRoot("Startpage.fxml");
        // Laden der neuen Scene Startseite
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Startpage.fxml"));
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
         */
        FXMLFactory.setRoot("StartpageAdmin");

    }

    public void swapToCITAdmin(ActionEvent actionEvent) throws IOException{
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CIT.fxml"));
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
        */
         FXMLFactory.setRoot("CITAdmin");
    }

    public void swapToStatisticAdmin(ActionEvent actionEvent) throws IOException {
        /*
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Statistic.fxml"));
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
        */
        FXMLFactory.setRoot("StatisticAdmin");
    }

    public void swapToCITAdd(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CITAdd.fxml"));
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
