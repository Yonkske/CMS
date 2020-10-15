package org.dhbw;

import backend.database.DbCallerCir;
import backend.database.DbCallerCit;
import backend.usability.Cir;
import backend.usability.Cit;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.geometry.Side;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


public class StatisticController extends Controller {

    @FXML
    public Label adminLbl;
    @FXML
    public Button startpageBtn;
    @FXML
    public Button citBtn;
    @FXML
    public Button statisticBtn;
    @FXML
    public Button userBtn;
    @FXML
    public ChoiceBox<Cit> searchTf;
    @FXML
    public Button filterBtn;
    @FXML
    public Label cirLbl;
    @FXML
    public TextField numberCIRTf;
    @FXML
    public Label citLabel;
    @FXML
    public TextField numberCITTf;
    @FXML
    public PieChart adminPieChart;
    @FXML
    public BarChart adminStackedBarChart;


    public void initialize() throws SQLException {
        ObservableList<Cit> list = FXCollections.observableArrayList();
        searchTf.setItems(list);
        list.addAll(Cit.showAll());


        ArrayList<Cit> allCit = new ArrayList<Cit>();
        allCit = Cit.showAll();
        Cit currentCit;


        PieChart.Data slice[] = new PieChart.Data[10];
        for (int i = 0; i < allCit.size(); i++) {
            currentCit = allCit.get(i);
            slice[i] = new PieChart.Data(currentCit.getCitName(), DB_CALLER_CIR.getCirCountForType(currentCit));
            adminPieChart.getData().add(slice[i]);
        }

        numberCIRTf.setText(String.valueOf(Cir.getCount()));


    }

    public void swapToStartpage(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

    public void swapToCIT(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("CIT");
    }

    public void swapToStatistic(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("Statistic");
    }

    public void swapToBenutzer(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("UserAdmin");
    }
}
