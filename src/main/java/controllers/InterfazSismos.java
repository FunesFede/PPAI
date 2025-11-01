package controllers;

import application.App;
import gestores.GestorRevision;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import utilities.Paths;

import java.util.ArrayList;

public class InterfazSismos {
    private GestorRevision gestor;
    private Stage stage;
    private App app;

    @FXML
    private Button btnCancelarRevision;

    @FXML
    private Button btnRevisarEvento;

    @FXML
    private TableColumn<String[], String> colCoordenadasEpicentro;

    @FXML
    private TableColumn<String[], String> colCoordenadasHipocentro;

    @FXML
    private TableColumn<String[], String> colMagnitud;

    @FXML
    private TableColumn<String[], String> columnOcurrencia;

    @FXML
    private Label labelMensaje;

    @FXML
    private TableView<String[]> tableEventosSismicos;

    @FXML
    void cancelarRevision(ActionEvent event) {
        //this.gestor.cancelarRevision();
        System.out.println("Cancelar revision");
    }

    @FXML
    void tomarEventoSeleccionado(ActionEvent event) {
        this.gestor.tomarEventoSeleccionado(tableEventosSismicos.getSelectionModel().getSelectedItem());
    }

    @FXML
    void initialize() {
        this.gestor = new GestorRevision(this);
        this.gestor.nuevaRevision();
    }

    public void habilitarPantalla() {
        this.gestor.nuevaRevision();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setGestor(GestorRevision gestor) {
        this.gestor = gestor;
    }

    public void pedirSeleccionEvento(ArrayList<String[]> datosEventos) {
        columnOcurrencia.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()[0]));
        colCoordenadasEpicentro.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()[1]));
        colCoordenadasHipocentro.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()[2]));
        colMagnitud.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue()[3]));


        this.tableEventosSismicos.getItems().clear();
        this.tableEventosSismicos.getItems().addAll(datosEventos);
        this.tableEventosSismicos.refresh();
    }

    public void mostrarDatosEventoSismico(String alcance, String clasificacion, String origen, ArrayList<String[]> datosSerie) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.INTERFAZ_DATOS_ES));
        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            DatosEventoSismicoController controller = loader.getController();
            controller.setGestor(this.gestor);
            controller.setDatos(alcance, clasificacion, origen, datosSerie);

            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void habilitarOpcionVisualizarMapa() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.OPCION_VISUALIZAR_MAPA));
        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            OpcionVisualizarMapaController controller = loader.getController();
            controller.setGestor(this.gestor);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void solicitarOpcionModificacion() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.OPCION_MODIFICACION));
        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            OpcionModificarController controller = loader.getController();
            controller.setGestor(this.gestor);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void solicitarAccion() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.SOLICITAR_ACCION));
        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            SolicitarAccionController controller = loader.getController();
            controller.setGestor(this.gestor);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void mostrarSismograma() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(Paths.SISMOGRAMA));
        try {
            AnchorPane pane = loader.load();
            Scene scene = new Scene(pane);
            //SolicitarAccionController controller = loader.getController();
            //controller.setGestor(this.gestor);

            Stage stage = new Stage();
            stage.setScene(scene);
            stage.show();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}