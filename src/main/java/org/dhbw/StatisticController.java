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

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


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


    public void initialize(URL url, ResourceBundle resourceBundle) {

        super.initialize(url, resourceBundle);
        ArrayList<Cit> allCit = new ArrayList<Cit>();
        try {
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


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    @Override
    public void refresh() {

    }
}
