package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "origen_de_generacion")
public class OrigenDeGeneracion {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "origen_de_generacion_id")
    private Integer id;
    
    @Column(name = "descripcion")
    private String descripcion;
    @Column(name = "nombre")
    private String nombre;

    public OrigenDeGeneracion() {}

    public OrigenDeGeneracion(String descripcion, String nombre) {
        this.descripcion = descripcion;
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
