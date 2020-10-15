package org.dhbw;

import backend.usability.Cir;
import backend.usability.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class UserAdminController extends Controller {

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
