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
    private Button showCirBtn;
    @FXML
    private Button cirDeleteBtn;
    @FXML
    private Button cirAddBtn;
    @FXML
    private TableView<Cir> cirTable;
    @FXML
    private TableColumn<Cir, String> citColumn;
    @FXML
    private TableColumn<Cir, String> cirNameColumn;
    @FXML
    private Button userBtn;
    @FXML
    private Button citAddBtn;
    @FXML
    private Button citDeleteBtn;
    @FXML
    private TextField searchTf;
    @FXML
    private ComboBox<Cit> filterCitCb;
    @FXML
    private Label statusLbl;
    private ArrayList<Cir> allCir;
    private final Cit placeholder = new Cit(0, "CIT", new String[]{null, null, null, null, null, null, null});

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
        allCir = DB_CALLER_CIR.getRecords();
        filterCitCb.setValue(placeholder);
        refresh();

        if (!Controller.user.getIsAdmin()) {
            userBtn.setVisible(false);
            citAddBtn.setVisible(false);
            citDeleteBtn.setVisible(false);
        }

        this.setToolTips();
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
        if (Objects.nonNull(cirTable.getSelectionModel().getSelectedItem())) {
            openPopup(new NotificationController(cirTable.getSelectionModel().getSelectedItem().getCit()), "Notification.fxml", true);
            this.disableButtons();
        }
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
            this.disableButtons();
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
        this.disableButtons();
    }


    /**
     * Changes the content of the table to fit the search and selected filter
     */
    @FXML
    public void setTableWithFilterAndSearch() {
        setTableContent(filterCirs());
    }


    /**
     * This method is to show a selected cir in a popup.
     *
     * @throws IOException - IOException
     */
    public void showCir() throws IOException {
        if (Objects.nonNull(cirTable.getSelectionModel().getSelectedItem())) {
            openPopup(new CIRViewController(cirTable.getSelectionModel().getSelectedItem()), "CIRView.fxml", false);
            this.disableButtons();
        }
    }

    /**
     * This method says what to do by selecting a user and double clicking at a user.
     */
    @FXML
    public void clickAction(MouseEvent mouseEvent) {

        if (Objects.nonNull(cirTable.getSelectionModel().getSelectedItem())) {
            showCirBtn.setDisable(false);
            citDeleteBtn.setDisable(false);
            cirDeleteBtn.setDisable(false);

        }

        if (mouseEvent.getClickCount() == 2) {
            try {
                showCir();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Sets the table content to the given list of CIRs
     *
     * @param cirs to be displayed in the table
     */
    @FXML
    private void setTableContent(ArrayList<Cir> cirs) {
        cirTable.getItems().setAll(cirs);
        citColumn.setCellValueFactory(new PropertyValueFactory<>("CitName"));
        cirNameColumn.setCellValueFactory(new PropertyValueFactory<>("CirName"));
        if (cirs.stream().count() != 0) {
            statusLbl.setVisible(false);
        } else {
            statusLbl.setText("Keine CIRs für die angegebene Suche und Filter gefunden oder noch keine CIRs vorhanden!");
            statusLbl.setVisible(true);
        }
    }

    /**
     * Resets the search and refreshes the table
     */
    @FXML
    public void emptySearchTf() {
        searchTf.setText("");
        setTableWithFilterAndSearch();
    }

    /**
     * Defines what happens to refresh the page after a popup is closed
     */
    @Override
    public void refresh() {
        try {
            Cit temp = filterCitCb.getSelectionModel().getSelectedItem();
            filterCitCb.getItems().clear();

            filterCitCb.getItems().add(placeholder);

            ArrayList<Cit> allCits = DB_CALLER_CIT.getAllCits();
            filterCitCb.getItems().addAll(DB_CALLER_CIT.getAllCits());

            if (temp.getCitID() == 0) {
                filterCitCb.setValue(placeholder);
            } else {
                filterCitCb.setValue(getSelectedFilterCit(temp.getCitID(), allCits));
            }

            setTableContent(allCir);
            // DO IT!
        } catch (SQLException e) {
            e.printStackTrace();
        }
        allCir = DB_CALLER_CIR.getRecords();
        this.setTableWithFilterAndSearch();
    }

    /**
     * Gets the CIT with the given Id out of the given list of CITs
     *
     * @param id      int - id to be looked for
     * @param allCits ArrayList from which the CIT should be selected
     * @return the CIT with the given ID
     */
    private Cit getSelectedFilterCit(int id, ArrayList<Cit> allCits) {
        for (Cit cit : allCits) {
            if (cit.getCitID() == id) {
                return cit;
            }
        }
        return null;
    }

    /**
     * Filters all CIRs loaded into the RAM at initialize. If they match the selected
     * filter and the search criteria they are added to the ArrayList which is returned
     * after all loaded CIRs have been checked.
     *
     * @return ArrayList containing the CIRs matching the search criteria
     */
    private ArrayList<Cir> filterCirs() {
        ArrayList<Cir> filteredCirs = new ArrayList<>();
        String searchValue = searchTf.getText().toLowerCase();
        Cit selectedCit = filterCitCb.getSelectionModel().getSelectedItem();

        if (Objects.nonNull(selectedCit)) {
            if (selectedCit.getCitID() == 0) {

                allCir.forEach(record -> {
                    if (checkForSearchValue(record, searchValue)) {
                        filteredCirs.add(record);
                    }
                });
            } else {
                allCir.forEach(record -> {
                    if (checkForSearchValue(record, searchValue) && record.getCit().getCitID() == selectedCit.getCitID()) {
                        filteredCirs.add(record);
                    }
                });
            }
        }

        return filteredCirs;
    }

    /**
     * This method sets the showCIR and the delete Button disable as long as no user is selected.
     */
    @FXML
    private void disableButtons() {
        showCirBtn.setDisable(true);
        citDeleteBtn.setDisable(true);
        cirDeleteBtn.setDisable(true);
    }

    /**
     * Checks if the given CIR contains the given search value in either the record name or type name
     *
     * @param record      CIR - record to be checked
     * @param searchValue String - value to be checked for
     * @return true if the CIR contains the search value, false if not
     */
    private boolean checkForSearchValue(Cir record, String searchValue) {
        return record.getCirName().toLowerCase().contains(searchValue) || record.getCit().getCitName().toLowerCase().contains(searchValue);
    }

    /**
     * sets the tooltips;
     */
    private void setToolTips() {
        citAddBtn.setTooltip(new Tooltip("Neuen CI Type hinzufügen"));
        citDeleteBtn.setTooltip(new Tooltip("Ausgewählten CI Type löschen"));
        cirAddBtn.setTooltip(new Tooltip("Neuen CI Record hinzufügen"));
        cirDeleteBtn.setTooltip(new Tooltip("Ausgewählten CI Record löschen"));
        showCirBtn.setTooltip(new Tooltip("Ausgewählten CI Record anschauen"));

    }

}