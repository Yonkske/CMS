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
import java.util.List;
import java.util.ResourceBundle;

public class StartpageController extends Controller implements Initializable {

    @FXML private TableView<Cir> cirTable;
    @FXML private TableColumn<Cir, String> citColumn;
    @FXML private TableColumn<Cir, String> cirNameColumn;
    @FXML private Label adminLbl;
    @FXML private Button userBtn;
    @FXML private Button citEditBtn;
    @FXML private Button citDeleteBtn;
    @FXML private Button searchBtn;
    @FXML private TextField searchTf;
    @FXML private ComboBox<Cit> filterCitCb;

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
            //cirTable.getItems().setAll(DB_CALLER_CIR.getAll());
            //citColumn.setCellValueFactory(new PropertyValueFactory<Cir, String>("CitName"));
            //cirNameColumn.setCellValueFactory(new PropertyValueFactory<Cir, String>("CirName"));
            setTableContent(DB_CALLER_CIR.getAll());

            filterCitCb.getItems().add(new Cit(0,new String[]{"CIT", null, null, null, null, null, null, null}));
            filterCitCb.getItems().addAll(DB_CALLER_CIT.getAllCits());
            // DO IT!
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // FIXME: For test purposes only
        super.user = new User("foobar", "foobar", false, true, "Simon", "Froehner");


        if(!super.user.getIsAdmin()) {
            adminLbl.setText(super.user.getSurName()+", "+super.user.getName());
            userBtn.setVisible(false);
            citEditBtn.setVisible(false);
            citDeleteBtn.setVisible(false);
        } else {
            adminLbl.setText(super.user.getSurName()+", "+super.user.getName()+" (Admin)");
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
    public void swapToUserAdmin() throws IOException{
        FXMLFactory.setRoot("UserAdmin");
    }

    /**
     * Opens the CITAdd Popup on button click
     *
     * @throws IOException
     */
    @FXML
    public void openAddCit() throws IOException {
        CITAddController citAddController = new CITAddController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CITAdd.fxml"));
        loader.setController(citAddController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    /**
     * Opens the Notification Popup on button click and gives the selected Cit that has to be deleted
     *
     * @throws IOException
     */
    @FXML
    public void openDeleteCitPopup() throws IOException {
        // FIXME
        //NotificationController notificationController = new NotificationController(cirTable.getSelectionModel().getSelectedItem().getCit());
        NotificationController notificationController = new NotificationController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notification.fxml"));
        loader.setController(notificationController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    /**
     * Opens the Notification Popup on button click and gives the selected Cir that has to be deleted
     *
     * @throws IOException
     */
    @FXML
    public void openDeleteCirPopup() throws IOException {
        // FIXME
        //NotificationController notificationController = new NotificationController(cirTable.getSelectionModel().getSelectedItem());
        NotificationController notificationController = new NotificationController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Notification.fxml"));
        loader.setController(notificationController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    /**
     * Opens the CIRAdd Popup on button click
     *
     * @throws IOException
     */
    @FXML
    public void openAddCir() throws IOException {
        CIRAddController cirAddController = new CIRAddController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CIRAdd.fxml"));
        loader.setController(cirAddController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    /**
     * Opens the CIREdit Popup on button click and gives the selected cir that has to be edited
     *
     * @throws IOException
     */
    @FXML
    public void openEditCir() throws IOException {
        // FIXME
        CIREditController CIREditController = new CIREditController(cirTable.getSelectionModel().getSelectedItem());
        //CIREditController CIREditController = new CIREditController();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("CIREdit.fxml"));
        loader.setController(CIREditController);
        Parent root = loader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        scene.getWindow().sizeToScene();
        stage.show();
    }

    @FXML
    public void setFilterForTable() {
        // TODO: get selected Cit
        Cit selectedCit = filterCitCb.getSelectionModel().getSelectedItem();
        // TODO: set filter for selected Cit
        try {
            if (selectedCit.getCitID() == 0)
            {
                setTableContent(DB_CALLER_CIR.getAll());
            } else {
                setTableContent(DB_CALLER_CIR.getAllCirForType(selectedCit));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void searchCirOrCit() {
        // TODO: get content of searchTf

        /* TODO: change table content to show only Cirs that contain input of searchTf
           in either citName or cirName
         */

    }

    private void setTableContent(ArrayList<Cir> cirs) {
        cirTable.getItems().setAll(cirs);
        citColumn.setCellValueFactory(new PropertyValueFactory<Cir, String>("CitName"));
        cirNameColumn.setCellValueFactory(new PropertyValueFactory<Cir, String>("CirName"));
    }
}
