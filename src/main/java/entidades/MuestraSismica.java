package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "muestra_sismica")
@Data
public class MuestraSismica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "muestra_sismica_id")
    private Integer id;

    @Column(name = "fecha_hora_muestra")
    private Date fechaHoraMuestra;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @JoinColumn(name = "muestra_sismica_id")
    private List<DetalleMuestraSismica> detalleMuestraSismica;

    public MuestraSismica() {}

    public MuestraSismica(Date fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
        this.detalleMuestraSismica = new ArrayList<>();
        detalleMuestraSismica.add(new DetalleMuestraSismica(45.5, new TipoDeDato("Velocidad Onda", "m/s", 30.0)));
        detalleMuestraSismica.add(new DetalleMuestraSismica(500.0, new TipoDeDato("Frecuencia Onda", "mhz", 30.0)));
        detalleMuestraSismica.add(new DetalleMuestraSismica(155.0, new TipoDeDato("Longitud", "m", 30.0)));
    }

    public void crearDetalleMuestra(Double valor, TipoDeDato tipoDeDato) {
        DetalleMuestraSismica nuevoDetalle = new DetalleMuestraSismica(valor, tipoDeDato);
        this.detalleMuestraSismica.add(nuevoDetalle);
    }

    public String[] getDatos(String nombreEstacion) {
        String fechaHora = this.fechaHoraMuestra.toString();
        String velocidad = "N/A";
        String frecuencia = "N/A";
        String longitud = "N/A";

        for (DetalleMuestraSismica detalle : this.detalleMuestraSismica) {
            String tipo = detalle.getTipoDeDato().getDenominacion(); // CAMBIAR METODO
            String valor = detalle.getDatos();

            if (tipo.equalsIgnoreCase("Velocidad Onda")) {
                velocidad = valor;
            } else if (tipo.equalsIgnoreCase("Frecuencia Onda")) {
                frecuencia = valor;
            } else if (tipo.equalsIgnoreCase("Longitud")) {
                longitud = valor;
            }
        }

        return new String[] { nombreEstacion, fechaHora, frecuencia, longitud, velocidad };
    }


    public Date getFechaHoraMuestra() {
        return fechaHoraMuestra;
    }
    public void setFechaHoraMuestra(Date fechaHoraMuestra) {
        this.fechaHoraMuestra = fechaHoraMuestra;
    }
    public List<DetalleMuestraSismica> getDetalleMuestraSismica() {
        return detalleMuestraSismica;
    }
    public void removeDetalleMuestra(DetalleMuestraSismica detalleMuestraSismica) {
        this.detalleMuestraSismica.remove(detalleMuestraSismica);
    }
}
