package org.dhbw;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;

import java.awt.*;

public class UserAddAdminController {

    @FXML public Label surnameLbl;
    @FXML public Label nameLbl;
    @FXML public Label usernameLbl;
    @FXML public Label authorizationLbl;
    @FXML public Label initialPasswordLbl;
    @FXML public Label repeatInitialPasswordLbl;
    @FXML public TextField surnameTf;
    @FXML public TextField nameTf;
    @FXML public TextField usernameTf;
    @FXML public TextField initialPasswordTf;
    @FXML public TextField repeatInitialPasswordTf;
    @FXML public ChoiceBox authorizationChoiceBox;
    @FXML public Button submitBtn;
    @FXML public Button cancelBtn;
}
