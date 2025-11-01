package controllers;

import gestores.GestorRevision;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class OpcionModificarController {
    private GestorRevision gestor;

    @FXML
    private Button btnNoModificar;

    @FXML
    private Button btnModificar;

    @FXML
    private Label labelVisualizar;

    @FXML
    void tomarOpcionNoModificar(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.close();
        this.gestor.tomarOpcionNoModificar();
    }

    @FXML
    void tomarOpcionModificar(ActionEvent event) {
        System.out.println("Ver Mapa");
    }

    public void setGestor(GestorRevision gestor) {
        this.gestor = gestor;
    }

}
