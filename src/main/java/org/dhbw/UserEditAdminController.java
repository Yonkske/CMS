package org.dhbw;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.awt.*;

public class UserEditAdminController {

    @FXML public Label surnameLbl;
    @FXML public Label nameLbl;
    @FXML public Label usernameLbl;
    @FXML public Label authorizationLbl;
    @FXML public TextField surnameTf;
    @FXML public TextField nameTf;
    @FXML public TextField usernameTf;
    @FXML public ChoiceBox authorizationChoiceBox;
    @FXML public Button submitBtn;
    @FXML public Button cancelBtn;
}
