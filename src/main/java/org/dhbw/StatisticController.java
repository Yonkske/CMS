package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;


public class StatisticController extends MainPagesController {

    @FXML
    public Button startpageBtn;
    @FXML
    public Button citBtn;
    @FXML
    public Button statisticBtn;
    @FXML
    public Button userBtn;
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

    /**
     * Methode from the interface Initializable that auto generates the page on
     * start
     *
     * @param url            - demanded by interface
     * @param resourceBundle - demanded by interface
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        super.initialize(url, resourceBundle);

        if (!Controller.user.getIsAdmin()) {
            userBtn.setVisible(false);
        }

        setUpChart();
    }

    private void setUpChart() {
        try {
            ArrayList<Cit> allCit = Cit.showAll();
            PieChart.Data[] slice = new PieChart.Data[10];

            for (int i = 0; i < allCit.size(); i++) {
                slice[i] = new PieChart.Data(allCit.get(i).getCitName(), DB_CALLER_CIR.getCirCountForType(allCit.get(i)));
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
