package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class StartpageController extends MainPagesController {

    @FXML
    private TableView<Cir> cirTable;
    @FXML
    private TableColumn<Cir, String> citColumn;
    @FXML
    private TableColumn<Cir, String> cirNameColumn;
    @FXML
    private Button userBtn;
    @FXML
    private Button citEditBtn;
    @FXML
    private Button citDeleteBtn;
    @FXML
    private TextField searchTf;
    @FXML
    private ComboBox<Cit> filterCitCb;

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
        try {
            setTableContent(DB_CALLER_CIR.getAll());

            Cit placeholder = new Cit(0, new String[]{"CIT", null, null, null, null, null, null, null});
            filterCitCb.getItems().add(placeholder);
            filterCitCb.getItems().addAll(DB_CALLER_CIT.getAllCits());
            filterCitCb.setValue(placeholder);
            // DO IT!
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!Controller.user.getIsAdmin()) {
            userBtn.setVisible(false);
            citEditBtn.setVisible(false);
            citDeleteBtn.setVisible(false);
        }

    }

    /**
     * Opens the CITAdd Popup on button click
     *
     * @throws IOException - if fxml file isn't found
     */
    @FXML
    public void openAddCit() throws IOException {
        openPopup(new CITAddController(), "CITAdd.fxml", true);
    }

    /**
     * Opens the Notification Popup on button click and gives the selected Cit that has to be deleted
     *
     * @throws IOException - if fxml file isn't found
     */
    @FXML
    public void openDeleteCitPopup() throws IOException {
        openPopup(new NotificationController(cirTable.getSelectionModel().getSelectedItem().getCit()), "Notification.fxml", true);
    }

    /**
     * Opens the Notification Popup on button click and gives the selected Cir that has to be deleted
     *
     * @throws IOException - if fxml file isn't found
     */
    @FXML
    public void openDeleteCirPopup() throws IOException {
        if (Objects.nonNull(cirTable.getSelectionModel().getSelectedItem())) {
            openPopup(new NotificationController(cirTable.getSelectionModel().getSelectedItem()), "Notification.fxml", true);
        }
    }

    /**
     * Opens the CIRAdd Popup on button click
     *
     * @throws IOException - if fxml file isn't found
     */
    @FXML
    public void openAddCir() throws IOException {
        openPopup(new CIRAddController(), "CIRAdd.fxml", true);
    }

    /**
     * Opens the CIREdit Popup on button click and gives the selected cir that has to be edited
     *
     * @throws IOException - if fxml file isn't found
     */
    @FXML
    public void openEditCir() throws IOException {
        if (Objects.nonNull(cirTable.getSelectionModel().getSelectedItem())) {
            openPopup(new CIREditController(cirTable.getSelectionModel().getSelectedItem()), "CIREdit.fxml", true);
        }
    }

    /**
     * Changes the content of the table to fit the search and selected filter
     */
    @FXML
    public void setTableWithFilterAndSearch() {
        Cit selectedCit = filterCitCb.getSelectionModel().getSelectedItem();
        String searchValue = searchTf.getText();
        try {
            if (selectedCit.getCitID() == 0) {
                setTableContent(DB_CALLER_CIR.getAllCirSearchValue(searchValue));
            } else {
                setTableContent(DB_CALLER_CIR.getAllWithFilterAndSearch(selectedCit, searchValue));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens the CirView on double click on table row
     *
     * @param me MouseEvent from javafx.scene.input
     * @throws IOException - if fxml file isn't found
     */
    @FXML
    public void openCirView(MouseEvent me) throws IOException {
        if (Objects.nonNull(cirTable.getSelectionModel().getSelectedItem()) && me.getClickCount() == 2) {
            openPopup(new CIRViewController(cirTable.getSelectionModel().getSelectedItem()), "CIRView.fxml", false);
        }
    }

    /**
     * Sets the table content to the given list of CIRs
     *
     * @param cirs to be displayed in the table
     */
    private void setTableContent(ArrayList<Cir> cirs) {
        cirTable.getItems().setAll(cirs);
        citColumn.setCellValueFactory(new PropertyValueFactory<>("CitName"));
        cirNameColumn.setCellValueFactory(new PropertyValueFactory<>("CirName"));
    }

    @Override
    public void refresh() {
        this.setTableWithFilterAndSearch();
    }

}