package org.dhbw;

import backend.usability.Cir;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class StartpageAdminController extends Controller implements Initializable {

    @FXML private TableView<Cir> cirTable;
    @FXML private TableColumn<Cir, String> citColumn;
    @FXML private TableColumn<Cir, String> cirNameColumn;

    @FXML
    public void swapToStartpage() throws IOException {
        FXMLFactory.setRoot("StartpageAdmin");
    }

    @FXML
    public void swapToCitAdmin() throws IOException {
        FXMLFactory.setRoot("CITAdmin");
    }

    @FXML
    public void swapToStatisticAdmin() throws IOException {
        FXMLFactory.setRoot("StatisticAdmin");
    }

    @FXML
    public void swapToUserAdmin() throws IOException{
        FXMLFactory.setRoot("UserAdmin");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        try {
            cirTable.getItems().setAll(DB_CALLER_CIR.getAll());
            citColumn.setCellValueFactory(new PropertyValueFactory<Cir, String>("CitID"));
            cirNameColumn.setCellValueFactory(new PropertyValueFactory<Cir, String>("CirName"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
