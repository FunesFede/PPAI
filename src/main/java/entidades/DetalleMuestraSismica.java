package entidades;

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
@Table(name = "detalle_muestra_sismica")
public class DetalleMuestraSismica {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "detalle_muestra_sismica_id")
    private Integer id;

    @Column(name = "valor")
    private Double valor;

    @ManyToOne
    @JoinColumn(name = "tipo_de_dato_id")
    private TipoDeDato tipoDeDato;

    public DetalleMuestraSismica() {}

    public DetalleMuestraSismica(Double valor, TipoDeDato tipoDeDato) {
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }
    public String getDatos() {
        return this.valor + this.tipoDeDato.getNombreUnidadMedida();
    }

    public Double getValor() {
        return this.valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public TipoDeDato getTipoDeDato() {
        return this.tipoDeDato;
    }
    public void setTipoDeDato(TipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }
}
