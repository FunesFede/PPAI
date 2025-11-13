package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "modelo_sismografo")
public class ModeloSismografo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "modelo_sismografo_id")
    private Integer id;
    @Column(name = "caracteristicas")
    private String caracteristicas;
    @Column(name = "nombre_modelo")
    private String nombreModelo;
    @ManyToOne
    @JoinColumn(name = "fabricante_id")
    private Fabricante fabricante;

    public ModeloSismografo() {}

    public ModeloSismografo(String caracteristicas, String nombreModelo, Fabricante fabricante) {
        this.caracteristicas = caracteristicas;
        this.nombreModelo = nombreModelo;
        this.fabricante = fabricante;
    }

    public String getCaracteristicas() {
        return this.caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public String getNombreModelo() {
        return this.nombreModelo;
    }
    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    public Fabricante getFabricante() {
        return this.fabricante;
    }
    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
}
