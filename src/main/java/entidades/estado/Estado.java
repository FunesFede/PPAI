package entidades.estado;

import java.util.Date;

import entidades.Empleado;
import entidades.EventoSismico;
import javafx.event.Event;

public class Estado {
    protected String nombreEstado;
        
    public Estado() {
        this.nombreEstado = "Estado";
    }

    // public boolean sosAutoDetectado() {
    //     return this.nombreEstado.equalsIgnoreCase("auto detectado");
    // }

    // public boolean sosRechazado() {
    //     return this.nombreEstado.equalsIgnoreCase("rechazado");
    // }

    // public boolean sosBloqueadoEnRevision() {
    //     return this.nombreEstado.equalsIgnoreCase("bloqueado en revision");
    // }

    public String getNombreEstado() {
        return this.nombreEstado;
    }
    public void setNombreEstado(String nombreEstado) {
        this.nombreEstado = nombreEstado;
    }

    // public boolean sosConfirmado() {
    //     return this.nombreEstado.equalsIgnoreCase("confirmado");
    // }

    // public boolean sosDerivadoAExperto() {
    //     return this.nombreEstado.equalsIgnoreCase("derivado a experto");
    // }

    public void rechazar(Empleado responsable, EventoSismico eventoSismico) throws Exception {
        throw new Exception("No es posible cambiar de estado a rechazado");
    }

    public void revisar(Empleado responsable, EventoSismico eventoSismico) throws Exception {
        throw new Exception("No es posible cambiar de estado a Bloqueado En Revision");
    }

    public void confirmar(Empleado responsable, EventoSismico eventoSismico) throws Exception {
        throw new Exception("No es posible cambiar de estado a Confirmado");
    }

    public void solicitarRevision(Empleado responsable, EventoSismico eventoSismico) throws Exception {
        throw new Exception("No es posible cambiar de estado a Derivado A Experto");
    }

    public Date obtenerFechaHoraActual() {
        return new Date();
    }

    public boolean sosAutoDetectado() {
        return false;
    }

}
