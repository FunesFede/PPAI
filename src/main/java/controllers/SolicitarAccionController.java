package controllers;

import gestores.GestorRevision;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class SolicitarAccionController {
    private GestorRevision gestor;

    @FXML
    private ComboBox<String> comboSeleccionOpcion;

    @FXML
    private Label labelAccion;

    @FXML
    void onSeleccionOpcion(ActionEvent event) {
        if (comboSeleccionOpcion.getValue().equals("Rechazar Evento")) {
            boolean hecho = this.gestor.tomarAccionRechazarEvento();
            if (hecho) {
                this.comboSeleccionOpcion.setDisable(true);
                this.labelAccion.setText("Evento rechazado exitosamente!");
            } else {
                this.labelAccion.setText("El evento no pudo ser rechazado");
            }
        } else if (comboSeleccionOpcion.getValue().equals("Confirmar Evento")) {
            boolean hecho = this.gestor.tomarAccionConfirmar();
            if (hecho) {
                this.comboSeleccionOpcion.setDisable(true);
                this.labelAccion.setText("Evento confirmado exitosamente!");
            } else {
                this.labelAccion.setText("El evento no pudo ser confirmado");
            }
        }
        else if (comboSeleccionOpcion.getValue().equals("Solicitar revision a experto")) {
            boolean hecho = this.gestor.tomarAccionSolicitarRevision();
            if (hecho) {
                this.comboSeleccionOpcion.setDisable(true);
                this.labelAccion.setText("Solicitud de revision enviada exitosamente!");
            } else {
                this.labelAccion.setText("El evento no pudo elevado a un experto");
            }
        }
    }

    @FXML
    void initialize() {
        comboSeleccionOpcion.getItems().addAll("Confirmar Evento", "Rechazar Evento", "Solicitar revision a experto");
    }

    public void setGestor(GestorRevision gestor) {
        this.gestor = gestor;
    }

}

