package org.dhbw;

import javafx.event.ActionEvent;

import java.io.IOError;
import java.io.IOException;

public class CITAdminController extends Controller {

    public void swapToStartpageAdmin(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("StartpageAdmin.fxml");
    }

    public void swapToCITAdmin(ActionEvent actionEvent) throws IOException{
        FXMLFactory.setRoot("CITAdmin.fxml");
    }

    public void swapToStatisticAdmin(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("StatisticAdmin");
    }

    public void swapToCITAdd(ActionEvent actionEvent) throws IOException {
        FXMLFactory.setRoot("CITAdd");
    }
}
