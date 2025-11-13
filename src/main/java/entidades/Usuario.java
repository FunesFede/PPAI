package entidades;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "usuario")
public class Usuario {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "usuario_id")
    private Integer id;

    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Column(name = "contrasena")
    private String contrasena;

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(
        name = "usuario_perfil",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "perfil_id")
    )
    private List<Perfil> perfil;

    @ManyToOne
    @JoinColumn(name = "suscripcion_id")
    private Suscripcion suscripcion;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado empleado;

    public Usuario() {}

    public Usuario(String nombreUsuario, String contrasena, Empleado empleado) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.empleado = empleado;
    }

    public Empleado getEmpleado() {
        return this.empleado;
    }
    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public String getNombreUsuario() {
        return this.nombreUsuario;
    }
    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public List<Perfil> getPerfil() {
        return new ArrayList<>(this.perfil);
    }
    public void addPerfil(Perfil perfil) {
        this.perfil.add(perfil);
    }
    public void removePerfil(Perfil perfil) {
        this.perfil.remove(perfil);
    }

    public Suscripcion getSuscripcion() {
        return suscripcion;
    }
    public void setSuscripcion(Suscripcion suscripcion) {
        this.suscripcion = suscripcion;
    }
}
