package entidades;

import java.util.ArrayList;
import java.util.Date;

import entidades.estado.Estado;

public class CambioEstado {
    private Date fechaHoraFin;
    private Date fechaHoraInicio;
    private Estado estado;
    private ArrayList<MotivoFueraServicio> motivoFueraServicio;
    private Empleado responsableInspeccion;

    public CambioEstado(Date fechaHoraInicio, Estado estado) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
    }

    public CambioEstado(Date fechaHoraInicio, Estado estado, Empleado responsable) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.estado = estado;
        this.responsableInspeccion = responsable;
    }

    public boolean sosActual() {
        return this.fechaHoraFin == null;
    }

    public Date getFechaHoraFin() {
        return fechaHoraFin;
    }
    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public Date getFechaHoraInicio() {
        return fechaHoraInicio;
    }
    public void setFechaHoraInicio(Date fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public Estado getEstado() {
        return estado;
    }
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    public ArrayList<MotivoFueraServicio> getMotivoFueraServicio() {
        return motivoFueraServicio;
    }
    public void setMotivoFueraServicio(ArrayList<MotivoFueraServicio> motivoFueraServicio) {
        this.motivoFueraServicio = motivoFueraServicio;
    }

    public Empleado getResponsableInspeccion() {
        return responsableInspeccion;
    }

    public void setResponsable(Empleado responsable) {
        this.responsableInspeccion = responsable;
    }
}
