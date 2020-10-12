package org.dhbw;

import java.io.IOException;

import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        MainController.setRoot("startpageUser");


    }
}
