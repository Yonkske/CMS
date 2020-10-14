package org.dhbw;

import javafx.event.ActionEvent;

import java.io.IOException;

public class StatisticController extends Controller {

    public void swapToStartpage(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

    public void swapToCIT(ActionEvent actionEvent) throws IOException{
        FXMLFactory.setRoot("CIT");
    }

    public void swapToStatistic(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("Statistic");
    }

    public void swapToBenutzer(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("UserAdmin");
    }
}
