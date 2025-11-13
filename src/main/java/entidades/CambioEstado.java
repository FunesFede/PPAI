package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.estado.Estado;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cambio_estado")
@Data
public class CambioEstado {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cambio_estado_id")
    private Integer id;

    @Column(name = "fecha_hora_fin")
    private Date fechaHoraFin;
    @Column(name = "fecha_hora_inicio")
    private Date fechaHoraInicio;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    // private List<MotivoFueraServicio> motivoFueraServicio;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado responsableInspeccion;

    public CambioEstado() {}

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

    // public ArrayList<MotivoFueraServicio> getMotivoFueraServicio() {
    //     return motivoFueraServicio;
    // }
    // public void setMotivoFueraServicio(ArrayList<MotivoFueraServicio> motivoFueraServicio) {
    //     this.motivoFueraServicio = motivoFueraServicio;
    // }

    public Empleado getResponsableInspeccion() {
        return responsableInspeccion;
    }

    public void setResponsable(Empleado responsable) {
        this.responsableInspeccion = responsable;
    }
}
