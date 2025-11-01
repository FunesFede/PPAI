// En App.java
package application;

import gestores.GestorRevision;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.Paths;

public class App extends Application {
    public static App app;
    private Stage stageWindow;
    private GestorRevision gestor;

    public static void main(String[] args) {
        launch(args);
    }

    public void setScene(String path) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(path));
        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);

            stageWindow.setScene(scene);
            stageWindow.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        app = this;
        this.stageWindow = stage;
        setScene(Paths.BIENVENIDA); // Ajustar según tus paths
    }
}