package org.dhbw;

import backend.usability.Cit;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;

import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


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

    public void fillComboBox() throws SQLException {

    }

}
