package org.dhbw;


import backend.database.DbConnector;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


/**
 * Factory builds FXML Objects at first.
 */
public class FXMLFactory extends Application {
    private static Scene scene;


    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("Login"));
        stage.setScene(scene);
        stage.setResizable(false);
        stage.getIcons().add(new Image(App.class.getResourceAsStream("icons/favicon.png")));
        stage.setTitle("CMS - Configuration Management System");
        stage.show();
        stage.setOnCloseRequest(windowEvent -> DbConnector.closeConnection());
    }

    /**
     * Sets root of actual scene to scene in file of @param fxml. Scene is set to new size.
     *
     * @param fxml String name <b>without</b> suffix.
     * @throws IOException the exception.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        setUpStage(scene, fxml);
    }

    /**
     * Sets root of actual scene to scene in file of @param fxml. Scene is set to new size.
     *
     * @param fxml            String name <b>without</b> suffix.
     * @param setSizeToScreen if true, the window will be set to the standard size of the scene
     * @throws IOException the exception.
     */
    static void setRoot(String fxml, boolean setSizeToScreen) throws IOException {
        scene.setRoot(loadFXML(fxml));
        if (setSizeToScreen) {
            scene.getWindow().sizeToScene();
        }
        setUpStage(scene, fxml);
    }

    /**
     * Sets up the stage
     *
     * @param scene scene which should be displayed on the stage
     * @param fxml  String name <b>without</b> suffix.
     */
    private static void setUpStage(Scene scene, String fxml) {
        Stage stage = (Stage) scene.getWindow();
        if (fxml.equals("Startpage") || fxml.equals("CIT") || fxml.equals("UserAdmin") || fxml.equals("Statistic")) {
            stage.setResizable(true);
            stage.setMinWidth(575);
            stage.setWidth(stage.getWidth());
            stage.setMinHeight(575);
            stage.setHeight(stage.getHeight());
            Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
            double x = bounds.getMinX() + (bounds.getWidth() - scene.getWidth()) * 0.5;
            double y = bounds.getMinY() + (bounds.getHeight() - scene.getHeight()) * 0.5;
            stage.setX(x);
            stage.setY(y);
        } else {
            stage.setResizable(false);
        }
    }

    /**
     * @param fxml String name without suffix.
     * @return FXMLLoader instance, loaded
     * @throws IOException the exception.
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader;

        // Logic that shows an error message instead of the login page if the database is already in use
        if (fxml.equals("Login")) {
            try {
                fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
                fxmlLoader.setController(new LoginController(true));
            } catch (SQLException e) {
                fxmlLoader = new FXMLLoader(App.class.getResource("Notification.fxml"));
                fxmlLoader.setController(new NotificationController("Die Datenbank ist bereits in Verwendung. Bitte schließen" +
                        " Sie alle laufenenden Instanzen des Programms und starten Sie das Programm neu."));
            }
        } else {
            fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        }

        return fxmlLoader.load();
    }

    /**
     * go method, extends main method of the program
     */
    void go() {
        launch();
    }
}
