package controllers;

import application.App;
import gestores.GestorRevision;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;

import java.util.ArrayList;

public class DatosEventoSismicoController {
    private static DatosEventoSismicoController instance;
    private GestorRevision gestor;
    private App app;

    @FXML
    private Text alcanceSismoText;

    @FXML
    private Button btnCancelarRevision;

    @FXML
    private Text clasSismoText;

    @FXML
    private Text origenGentext;

    @FXML
    private TableView<String[]> tableSeries;

    @FXML
    private TableColumn<String[], String> colEstacion;

    @FXML
    private TableColumn<String[], String> colFechaHora;

    @FXML
    private TableColumn<String[], String> colFrecuencia;

    @FXML
    private TableColumn<String[], String> colLongitud;

    @FXML
    private TableColumn<String[], String> colVelocidad;

    @FXML
    public void cancelarRevisionEvento(ActionEvent actionEvent) {
        // this.gestor.cancelarRevision();
        System.out.println("Cancelar revision");
    }

    @FXML
    void initialize() {
        colEstacion.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()[0]));
        colFechaHora.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()[1]));
        colFrecuencia.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()[2]));
        colLongitud.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()[3]));
        colVelocidad.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()[4]));

        instance = this;
    }

    public void setGestor(GestorRevision gestor) {
        this.gestor = gestor;
    }

    public void setDatos(String alcance, String clasificacion, String origen, ArrayList<String[]> datosSerie) {
        alcanceSismoText.setText(alcance);
        clasSismoText.setText(clasificacion);
        origenGentext.setText(origen);

        ObservableList<String[]> datosTabla = FXCollections.observableArrayList(datosSerie);

        tableSeries.getItems().clear();
        tableSeries.getItems().addAll(datosTabla);
        tableSeries.refresh();
    }

}