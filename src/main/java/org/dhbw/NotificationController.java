package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import backend.usability.User;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NotificationController extends Controller implements Initializable {

    @FXML
    private Button yesBtn;
    @FXML
    private Button noBtn;
    @FXML
    private Label notificationLbl;


    private Cit citToDelete;
    private Cir cirToDelete;
    private User userToDelete;
    private String itemToDelete;
    private String callingPage;
    private String customMessage;

    public NotificationController() {
    }

    /**
     * Creates a new NotificationController for deleting an user
     *
     * @param inUser      - the user that you want to delete
     * @param callingPage - name of the fxml file to be refreshed
     */
    public NotificationController(User inUser, String callingPage) {
        this.userToDelete = inUser;
        this.itemToDelete = "user";
        this.callingPage = callingPage;
    }

    /**
     * Creates a new NotificationController for deleting an user
     *
     * @param inUser - the user that you want to delete
     */
    public NotificationController(User inUser) {
        this.userToDelete = inUser;
        this.itemToDelete = "user";
    }

    /**
     * Creates a new NotificationController for deleting a CIT
     *
     * @param inCit       - the cit that you want to delete
     * @param callingPage - name of the fxml file to be refreshed
     */
    public NotificationController(Cit inCit, String callingPage) {
        this.citToDelete = inCit;
        this.itemToDelete = "cit";
        this.callingPage = callingPage;
    }

    /**
     * Creates a new NotificationController for deleting a CIT
     *
     * @param inCit - the cit that you want to delete
     */
    public NotificationController(Cit inCit) {
        this.citToDelete = inCit;
        this.itemToDelete = "cit";
    }

    /**
     * Creates a new NotificationController for deleting a CIR
     *
     * @param inCir       - the cir that you want to delete
     * @param callingPage - name of the fxml file to be refreshed
     */
    public NotificationController(Cir inCir, String callingPage) {
        this.cirToDelete = inCir;
        this.itemToDelete = "cir";
        this.callingPage = callingPage;
    }

    /**
     * Creates a new NotificationController for deleting CIR
     *
     * @param inCir - the cir that you want to delete
     */
    public NotificationController(Cir inCir) {
        this.cirToDelete = inCir;
        this.itemToDelete = "cir";
    }

    /**
     * Creates a new NotificationController with a custom message
     *
     * @param message String - a custom message to be displayed in the popup
     */
    public NotificationController(String message) {
        this.customMessage = message;
        this.itemToDelete = "custom";
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        switch (itemToDelete) {
            case "cir":
                notificationLbl.setText("Sind Sie sicher, dass sie den CIR \"" + cirToDelete.getCirName() + "\" und löschen wollen?");
                break;
            case "cit":
                notificationLbl.setText("Sind Sie sicher, dass sie den CIT \"" + citToDelete.getCitName() + "\" und alle zugehörigen CIRs löschen wollen?");
                break;
            case "user":
                notificationLbl.setText("Sind Sie sicher, dass sie den Benutzer \"" + userToDelete.getUserName() + "\" löschen wollen?");
                break;
            case "custom":
                notificationLbl.setText(customMessage);
                yesBtn.setVisible(false);
                noBtn.setText("OK");
            default:
        }
    }


    /**
     * Closes the notification popup
     */
    @FXML
    private void closeStage() {
        Stage stClose = (Stage) noBtn.getScene().getWindow();
        stClose.close();
    }

    /**
     * Deletes the given cit, cir or user when the yesBtn is clicked
     */
    @FXML
    private void yesButtonClicked() {

        switch (itemToDelete) {
            case "cir":
                DB_CALLER_CIR.deleteCir(cirToDelete);
                break;
            case "cit":
                DB_CALLER_CIT.deleteCit(citToDelete);
                break;
            case "user":
                DB_CALLER_USER.deleteUser(userToDelete);
                break;
            default:
                System.out.println("Löschen fehlgeschlagen!");
        }

        try {
            if (callingPage != null) {
                FXMLFactory.setRoot(callingPage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        closeStage();
    }
}
