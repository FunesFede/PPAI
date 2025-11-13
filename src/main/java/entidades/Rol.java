package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Entity
@Table(name = "rol")
@Data
public class Rol {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rol_id")
    private Integer id;

    @Column(name = "descripcion_rol")
    private String descripcionRol;
    @Column(name = "nombre")
    private String nombre;

    public Rol() {}

    public Rol(String descripcionRol, String nombre) {
        this.descripcionRol = descripcionRol;
        this.nombre = nombre;
    }

    public Rol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public String getDescripcionRol() {
        return this.descripcionRol;
    }
    public void setDescripcionRol(String descripcionRol) {
        this.descripcionRol = descripcionRol;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
