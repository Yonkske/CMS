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
        scene = new Scene(loadFXML("UserViewAdmin"));
        stage.setScene(scene);
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
