package org.dhbw;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
    }

    /**
     * Sets root of actual scene to scene in file of @param fxml. Scene is set to new size.
     *
     * @param fxml String name <b>without</b> suffix.
     * @throws IOException the exception.
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.getWindow().sizeToScene();
        Stage stage = (Stage) scene.getWindow();
        stage.setResizable(fxml.equals("Startpage") || fxml.equals("CIT") || fxml.equals("UserAdmin") || fxml.equals("Statistic"));
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
