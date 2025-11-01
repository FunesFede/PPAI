package controllers;

import application.App;
import gestores.GestorRevision;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.Paths;

public class BienvenidaPantallaController {
    private App app;

    @FXML
    private Button btnNuevaRevision;

    @FXML
    private Button btnSalir;

    @FXML
    void nuevaRevisionClick(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.INTERFAZ_SISMOS));
        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            InterfazSismos controller = loader.getController();
            //controller.setGestor(new GestorRevision(controller));
            //controller.habilitarPantalla();

            Stage stage = new Stage();
            controller.setStage(stage);
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void salirClick(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
    }

    public void setApp(App app) {
        this.app = app;
    }

}
