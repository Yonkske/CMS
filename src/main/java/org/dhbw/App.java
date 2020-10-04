package org.dhbw;

import backend.Backend;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App  {

    public static void main(String[] args) {
        new MainController().main(args);
    }
}