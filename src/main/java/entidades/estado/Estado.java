package entidades.estado;

import java.util.Date;

import entidades.Empleado;
import entidades.EventoSismico;

public class Estado {
    private String ambito;
    private String nombreEstado;
    
    public Estado() {
    }
    
    public Estado(String ambito, String nombreEstado) {
        this.ambito = ambito;
        this.nombreEstado = nombreEstado;
    }

    public boolean sosAutoDetectado() {
        return this.nombreEstado.equalsIgnoreCase("auto detectado");
    }

    public boolean sosRechazado() {
        return this.nombreEstado.equalsIgnoreCase("rechazado");
    }

    public boolean sosAmbitoEventoSismico() {
        return this.ambito.equalsIgnoreCase("evento sismico");
    }

    public boolean sosBloqueadoEnRevision() {
        return this.nombreEstado.equalsIgnoreCase("bloqueado en revision");
    }

    public String getAmbito() {
        return this.ambito;
    }
    public void setAmbito(String ambito) {
        this.ambito = ambito;
    }

    public String getNombreEstado() {
        return this.nombreEstado;
    }
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    public boolean sosConfirmado() {
        return this.nombreEstado.equalsIgnoreCase("confirmado");
    }

    public boolean sosDerivadoAExperto() {
        return this.nombreEstado.equalsIgnoreCase("derivado a experto");
    }

    public void rechazar(Empleado responsable, EventoSismico eventoSismico) throws Exception {
        throw new Exception("No es posible cambiar de estado a rechazado");
    }

    public void revisar(Empleado responsable, EventoSismico eventoSismico) throws Exception {
        throw new Exception("No es posible cambiar de estado a bloqueadoEnRevision");
    }

    public Date obtenerFechaHoraActual() {
        return new Date();
    }

}
