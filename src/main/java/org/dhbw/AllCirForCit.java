package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Function;

public class AllCirForCit extends Controller implements Initializable {


    @FXML
    public Button closeBtn;
    @FXML
    public ComboBox<Cit> comboBoxCit;
    @FXML
    public TableView<Cir> tableView;
    @FXML
    public TableColumn<Cir, String> tableColumnId;
    @FXML
    public TableColumn<Cir, String> tableColumnName;
    @FXML
    public TableColumn<Cir, String> column1;
    @FXML
    public TableColumn<Cir, String> column2;
    @FXML
    public TableColumn<Cir, String> column3;
    @FXML
    public TableColumn<Cir, String> column4;
    @FXML
    public TableColumn<Cir, String> column5;
    @FXML
    public TableColumn<Cir, String> column6;
    @FXML
    public TableColumn<Cir, String> column7;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fillChoiceBox();
    }

    private void fillChoiceBox() {
        try {
            comboBoxCit.getItems().addAll(DB_CALLER_CIT.getAllCits());
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }

    static private <S, T> Callback<TableColumn.CellDataFeatures<S, T>, ObservableValue<T>> createArrayValueFactory(Function<S, T[]> arrayExtractor, final int index) {
        if (index < 0) {
            return cd -> null;
        }
        return cd -> {
            T[] array = arrayExtractor.apply(cd.getValue());
            return array == null || array.length <= index ? null : new SimpleObjectProperty<>(array[index]);
        };
    }


    @FXML
    public void fillTableWithCir() throws SQLException {
        ArrayList<Cir> list = DB_CALLER_CIR.getAllCirForType(comboBoxCit.getSelectionModel().getSelectedItem());
        tableView.getItems().setAll(list);
        tableColumnName.setCellValueFactory(new PropertyValueFactory<>("CirName"));
        tableColumnId.setCellValueFactory(new PropertyValueFactory<>("CirID"));


        column1.setCellValueFactory(createArrayValueFactory(Cir::getCirAttributes, 0));
        column2.setCellValueFactory(createArrayValueFactory(Cir::getCirAttributes, 1));
        column3.setCellValueFactory(createArrayValueFactory(Cir::getCirAttributes, 2));
        column4.setCellValueFactory(createArrayValueFactory(Cir::getCirAttributes, 3));
        column5.setCellValueFactory(createArrayValueFactory(Cir::getCirAttributes, 4));
        column6.setCellValueFactory(createArrayValueFactory(Cir::getCirAttributes, 5));
        column7.setCellValueFactory(createArrayValueFactory(Cir::getCirAttributes, 6));

        this.fillTableHeaders(comboBoxCit.getSelectionModel().getSelectedItem());
    }

    private void fillTableHeaders(Cit cit) {
       column1.setText(cit.getCitAttributes()[1]);
       column2.setText(cit.getCitAttributes()[2]);
       column3.setText(cit.getCitAttributes()[3]);
       column4.setText(cit.getCitAttributes()[4]);
       column5.setText(cit.getCitAttributes()[5]);
       column6.setText(cit.getCitAttributes()[6]);
       column7.setText(cit.getCitAttributes()[7]);
    }


    public void close(ActionEvent actionEvent) {
        Stage stage = (Stage) closeBtn.getScene().getWindow();
        stage.close();
    }
}
