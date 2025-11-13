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
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "perfil")
public class Perfil {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "perfil_id")
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "nombre")
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "perfil_id")
    private List<Permiso> permisos;

    public Perfil() {}

    public Perfil(String descripcion, String nombre, List<Permiso> permisos) {
        this.descripcion = descripcion;
        this.nombre = nombre;
        this.permisos = permisos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Permiso> getPermisos() {
        return permisos;
    }
    public void addPermisos(Permiso permiso) {
        this.permisos.add(permiso);
    }
    public void removePermisos(Permiso permiso) {
        this.permisos.remove(permiso);
    }
}
