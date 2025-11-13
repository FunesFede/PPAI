package entidades;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "suscripcion")
public class Suscripcion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "suscripcion_id")
    private Integer id;
    @Column(name = "fecha_hora_inicio")
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    private Date fechaHoraFin;

    @ManyToOne
    @JoinColumn(name = "estacion_sismologica_id")
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
