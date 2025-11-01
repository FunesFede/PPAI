package entidades;

import java.util.Date;

public class Suscripcion {
    private Date fechaHoraInicio;
    private Date fechaHoraFin;
    private EstacionSismologica estacionSismologica;

    public Suscripcion(Date fechaHoraInicio, EstacionSismologica estacionSismologica) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estacionSismologica = estacionSismologica;
    }

    public boolean esVigente() {
        return fechaHoraFin == null;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public EstacionSismologica getEstacionSismologica() {
        return estacionSismologica;
    }

    public void setEstacionSismologica(EstacionSismologica estacionSismologica) {
        this.estacionSismologica = estacionSismologica;
    }
}
