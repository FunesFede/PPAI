package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "clasificacion_sismo")
public class ClasificacionSismo {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "clasificacion_sismo_id")
    private Integer id;

    @Column(name = "km_profundidad_desde")
    private Double kmProdundidadDesde;
    @Column(name = "km_profundidad_hasta")
    private Double kmProdundidadHasta;
    @Column(name = "nombre")
    private String nombre;

    public ClasificacionSismo() {}

    public ClasificacionSismo(Double kmProdundidadDesde, Double kmProdundidadHasta, String nombre) {
        this.kmProdundidadDesde = kmProdundidadDesde;
        this.kmProdundidadHasta = kmProdundidadHasta;
        this.nombre = nombre;
    }

    public Double getKmProdundidadDesde() {
        return this.kmProdundidadDesde;
    }
    public void setKmProdundidadDesde(Double kmProdundidadDesde) {
        this.kmProdundidadDesde = kmProdundidadDesde;
    }
    public Double getKmProdundidadHasta() {
        return this.kmProdundidadHasta;
    }
    public void setKmProdundidadHasta(Double kmProdundidadHasta) {
        this.kmProdundidadHasta = kmProdundidadHasta;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
