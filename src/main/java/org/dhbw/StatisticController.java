package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.util.ArrayList;


public class StatisticController extends MainPagesController {

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
        numberCITTf.setText(String.valueOf(Cit.getCount()));

    }

    @Override
    public void refresh() {

    }
}
