package controllers;

import gestores.GestorRevision;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OpcionVisualizarMapaController {
    private GestorRevision gestor;

    @FXML
    private Button btnNoVerMapa;

    @FXML
    private Button btnVerMapa;

    @FXML
    private Label labelVisualizar;

    @FXML
    void tomarNoVerMapa(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        this.gestor.tomarNoVerMapa();
    }

    @FXML
    void tomarVerMapa(ActionEvent event) {
        System.out.println("Ver Mapa");
    }

    public void setGestor(GestorRevision gestor) {
        this.gestor = gestor;
    }

}
