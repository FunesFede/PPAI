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
@Table(name = "sesion")
public class Sesion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sesion_id")
    private Integer id;

    @Column(name = "fecha_hora_inicio")
    private Date fechaHoraInicio;
    @Column(name = "fecha_hora_fin")
    private Date fechaHoraFin;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    public Sesion() {}

    public Sesion(Date fechaHoraInicio, Usuario usuario) {
        this.fechaHoraInicio = fechaHoraInicio;
        this.usuario = usuario;
    }

    public Empleado obtenerAnalistaSismosLogueado() {
        return this.usuario.getEmpleado();
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
}
