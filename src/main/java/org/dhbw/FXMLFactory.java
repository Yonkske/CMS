package org.dhbw;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


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
        stage.show();
    }

    /**
     * Sets root of actual scene to scene in file of @param fxml. Scene is set to new size.
     *
     * @param fxml String name <b>without</b> suffix.
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
        scene.getWindow().sizeToScene();
        if (fxml.equals("Startpage") || fxml.equals("CIT") || fxml.equals("UserAdmin") || fxml.equals("Statistic")) {
            Stage stage = (Stage) scene.getWindow();
            stage.setResizable(true);
        }
    }

    /**
     * @param fxml String name without suffix.
     * @return FXMLLoader instance, loaded
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * go method, extends main method of the program
     */
    void go() {
        new Controller();
        launch();
    }
}
