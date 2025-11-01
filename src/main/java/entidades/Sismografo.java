package entidades;

import java.util.ArrayList;
import java.util.Date;

public class Sismografo {
    private Date fechaAdquisicion;
    private String identificadorSismografo;
    private String nroSerie;
    private Estado estadoActual;
    private ArrayList<CambioEstado> cambioEstado;
    private ArrayList<SerieTemporal> serieTemporal;
    private ModeloSismografo modeloSismografo;
    private EstacionSismologica estacionSismologica;
    // private Reparacion reparacion;

    public  Sismografo(Date fechaAdquisicion, String identificadorSismografo, String nroSerie, EstacionSismologica estacionSismologica) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.cambioEstado = new ArrayList<>();
        this.serieTemporal = new ArrayList<>();
        this.estacionSismologica = estacionSismologica;
    }

    public String obtenerNombreEstacion() {
        return this.estacionSismologica.getNombre();
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getIdentificadorSismografo() {
        return identificadorSismografo;
    }
    public void setIdentificadorSismografo(String identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public String getNroSerie() {
        return nroSerie;
    }
    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }
    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public ArrayList<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }
    public void addCambioEstado(CambioEstado cambioEstado) {
        this.cambioEstado.add(cambioEstado);
    }
    public void removeCambioEstado(CambioEstado cambioEstado) {
        this.cambioEstado.remove(cambioEstado);
    }

    public ModeloSismografo getModeloSismografo() {
        return modeloSismografo;
    }
    public void setModeloSismografo(ModeloSismografo modeloSismografo) {
        this.modeloSismografo = modeloSismografo;
    }

    public ArrayList<SerieTemporal> getSerieTemporal() {
        return serieTemporal;
    }
    public void addSerieTemporal(SerieTemporal nuevaSerieTemporal) {
        this.serieTemporal.add(nuevaSerieTemporal);
    }
    public void removeSerieTemporal(SerieTemporal nuevaSerieTemporal) {
        this.serieTemporal.remove(nuevaSerieTemporal);
    }

}
