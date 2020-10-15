package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import backend.usability.User;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class StartpageController extends Controller implements Initializable {

    @FXML
    private TableView<Cir> cirTable;
    @FXML
    private TableColumn<Cir, String> citColumn;
    @FXML
    private TableColumn<Cir, String> cirNameColumn;
    @FXML
    private Label adminLbl;
    @FXML
    private Button userBtn;
    @FXML
    private Button citEditBtn;
    @FXML
    private Button citDeleteBtn;
    @FXML
    private Button searchBtn;
    @FXML
    private TextField searchTf;
    @FXML
    private ComboBox<Cit> filterCitCb;

    private final String PAGE_NAME = "Startpage";

    /**
     * Methode from the interface Initializable that auto generates the page on
     * start
     *
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

        // FIXME: For test purposes only
        super.user = new User("foobar", "foobar", false, false, "Simon", "Froehner");


        if (!super.user.getIsAdmin()) {
            adminLbl.setText(super.user.getSurName() + ", " + super.user.getName());
            userBtn.setVisible(false);
            citEditBtn.setVisible(false);
            citDeleteBtn.setVisible(false);
        } else {
            adminLbl.setText(super.user.getSurName() + ", " + super.user.getName() + " (Admin)");
        }
    }

    /**
     * Swaps the scene to the startpage on button click
     *
     * @throws IOException
     */
    @FXML
    public void swapToStartpage() throws IOException {
        FXMLFactory.setRoot("Startpage");
    }

    /**
     * Swaps the scene to the CITPage on button click
     *
     * @throws IOException
     */
    @FXML
    public void swapToCitAdmin() throws IOException {
        FXMLFactory.setRoot("CIT");
    }

    /**
     * Swaps the scene to the statistic page on button click
     *
     * @throws IOException
     */
    @FXML
    public void swapToStatisticAdmin() throws IOException {
        FXMLFactory.setRoot("Statistic");
    }

    /**
     * Swaps the scene to the user page on button click
     *
     * @throws IOException
     */
    @FXML
    public void swapToUserAdmin() throws IOException {
        FXMLFactory.setRoot("UserAdmin");
    }

    /**
     * Opens the CITAdd Popup on button click
     *
     * @throws IOException
     */
    @FXML
    public void openAddCit() throws IOException {
        openPopup(new CITAddController(), "CITAdd.fxml", true);
    }

    /**
     * Opens the Notification Popup on button click and gives the selected Cit that has to be deleted
     *
     * @throws IOException
     */
    @FXML
    public void openDeleteCitPopup() throws IOException {
        openPopup(new NotificationController(cirTable.getSelectionModel().getSelectedItem().getCit()), "Notifcation.fxml", true);
    }

    /**
     * Opens the Notification Popup on button click and gives the selected Cir that has to be deleted
     *
     * @throws IOException
     */
    @FXML
    public void openDeleteCirPopup() throws IOException {
        openPopup(new NotificationController(cirTable.getSelectionModel().getSelectedItem()), "Notifcation.fxml", true);
    }

    /**
     * Opens the CIRAdd Popup on button click
     *
     * @throws IOException
     */
    @FXML
    public void openAddCir() throws IOException {
        openPopup(new CIRAddController(), "CIRAdd.fxml", true);
    }

    /**
     * Opens the CIREdit Popup on button click and gives the selected cir that has to be edited
     *
     * @throws IOException
     */
    @FXML
    public void openEditCir() throws IOException {
        openPopup(new CIREditController(cirTable.getSelectionModel().getSelectedItem()), "CIREdit.fxml", true);
    }

    /**
     * Sets the content of the table to the given list of cirs
     *
     * @param cirs - ArrayList with cirs to be shown in the table
     */

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
     * Sets the table content to the given list of CIRs
     *
     * @param cirs to be displayed in the table
     */
    private void setTableContent(ArrayList<Cir> cirs) {
        cirTable.getItems().setAll(cirs);
        citColumn.setCellValueFactory(new PropertyValueFactory<Cir, String>("CitName"));
        cirNameColumn.setCellValueFactory(new PropertyValueFactory<Cir, String>("CirName"));
    }

    /**
     * Opens a popup window
     *
     * @param controller controller for the popup - must match fxmlName
     * @param fxmlName of the fxml file to be opened in the popup - must match controller
     * @param onHiding if the page should be refreshed on closing the popup
     * @throws IOException
     */
    private void openPopup(Controller controller, String fxmlName, boolean onHiding) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlName));
        loader.setController(controller);
        Parent root = loader.load();
        Stage stage = new Stage();
        if(onHiding) {
            stage.setOnHiding(windowEvent -> this.setTableWithFilterAndSearch());
        }
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }
}
