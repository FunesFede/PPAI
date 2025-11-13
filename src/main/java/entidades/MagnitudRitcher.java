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
@Table(name = "magnitud_ritcher")
public class MagnitudRitcher {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "magnitud_ritcher_id")
    private Integer id;
    @Column(name = "descripcion_magnitud")
    private String descripcionMagnitud;
    @Column(name = "numero")
    private Double numero;

    public MagnitudRitcher(String descripcionMagnitud, Double numero) {
        this.descripcionMagnitud = descripcionMagnitud;
        this.numero = numero;
    }

    public String getDescripcionMagnitud() {
        return this.descripcionMagnitud;
    }
    public void setDescripcionMagnitud(String descripcionMagnitud) {
            this.descripcionMagnitud = descripcionMagnitud;
    }

    public Double getNumero() {
        return this.numero;
    }
    public void setNumero(Double numero) {
        this.numero = numero;
    }
}
