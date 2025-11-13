package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "tipo_de_dato")
@Data
public class TipoDeDato {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipo_de_dato_id")
    private Integer id;

    @Column(name = "denominacion")
    private String denominacion;
    @Column(name = "nombre_unidad_medida")
    private String nombreUnidadMedida;
    @Column(name = "valor_umbral")
    private Double valorUmbral;

    public TipoDeDato() {}

    public TipoDeDato(String denominacion, String nombreUnidadMedida, Double valorUmbral) {
        this.denominacion = denominacion;
        this.nombreUnidadMedida = nombreUnidadMedida;
        this.valorUmbral = valorUmbral;
    }

    public boolean esTuDenominacion(String denominacion) {
        return this.denominacion.equals(denominacion);
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }
    public void setNombreUnidadMedida(String nombreUnidadMedida) {
        this.nombreUnidadMedida = nombreUnidadMedida;
    }
    public Double getValorUmbral() {
        return valorUmbral;
    }
    public void setValorUmbral(Double valorUmbral) {
        this.valorUmbral = valorUmbral;
    }
}
