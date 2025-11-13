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
@Table(name = "fabricante")
public class Fabricante {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fabricante_id")
    private Integer id;

    @Column(name = "nombre")
    private String nombre;
    @Column(name = "razon_social")
    private String razonSocial;

    public Fabricante() {}

    public Fabricante(String nombre, String razonSocial) {
        this.nombre = nombre;
        this.razonSocial = razonSocial;
    }

    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getRazonSocial() {
        return this.razonSocial;
    }
    public void setRazonSocial(String razonSocial) {
        this.razonSocial = razonSocial;
    }
}
