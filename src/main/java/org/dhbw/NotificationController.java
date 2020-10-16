package org.dhbw;

import backend.usability.Cir;
import backend.usability.Cit;
import backend.usability.User;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class NotificationController extends Controller  {

    @FXML
    public Button yesBtn;

    @FXML
    private Button noBtn;


    private Cit citToDelete;
    private Cir cirToDelete;
    private User userToDelete;
    private String itemToDelete;
    private String callingPage;

    public NotificationController() {
    }

    /**
     * Creates a new NotificationController
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
     * Creates a new NotificationController
     *
     * @param inUser - the user that you want to delete
     */
    public NotificationController(User inUser) {
        this.userToDelete = inUser;
        this.itemToDelete = "user";
    }

    /**
     * Creates a new NotificationController
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
     * Creates a new NotificationController
     *
     * @param inCit - the cit that you want to delete
     */
    public NotificationController(Cit inCit) {
        this.citToDelete = inCit;
        this.itemToDelete = "cit";
    }

    /**
     * Creates a new NotificationController
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
     * Creates a new NotificationController
     *
     * @param inCir - the cir that you want to delete
     */
    public NotificationController(Cir inCir) {
        this.cirToDelete = inCir;
        this.itemToDelete = "cir";
    }


    @FXML
    /**
     * Closes the notification popup
     */
    private void closeStage() {
        Stage stClose = (Stage) noBtn.getScene().getWindow();
        stClose.close();
    }

    @FXML
    /**
     * Deletes the given cit, cir or user when the yesBtn is clicked
     */
    private void yesButtonClicked() {

        switch (itemToDelete) {
            case "cir":
                try {
                    DB_CALLER_CIR.deleteCir(cirToDelete);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                break;
            case "cit":
                try {
                    DB_CALLER_CIT.deleteCit(citToDelete);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
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
