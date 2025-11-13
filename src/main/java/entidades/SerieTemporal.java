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
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "serie_temporal")
@Data
public class SerieTemporal {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "serie_temporal_id")
    private Integer id;

    @Column(name = "condicion_alarma")
    private String condicionAlarma;
    @Column(name = "fecha_hora_inicio_registro_muestras")
    private Date fechaHoraInicioRegistroMuestras;
    @Column(name = "fecha_hora_registro")
    private Date fechaHoraRegistro;
    @Column(name = "frecuencia_muestreo")
    private Double frecuenciaMuestreo;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch= FetchType.EAGER)
    @JoinColumn(name = "serie_temporal_id")
    private List<MuestraSismica> muestrasSismica;

    @ManyToOne
    @JoinColumn(name = "sismografo_id")
    private Sismografo sismografo;

    public SerieTemporal() {}

    public SerieTemporal(Sismografo sismografo) {
        this.sismografo = sismografo;

        muestrasSismica = new ArrayList<>();
        muestrasSismica.add(new MuestraSismica(new Date(2025-1900, 0, 15, 12, 0)));
        muestrasSismica.add(new MuestraSismica(new Date(2025-1900, 0, 15, 12, 2)));
    }

    public String obtenerNombreEstacion() {
        return this.sismografo.obtenerNombreEstacion();
    }

    public ArrayList<String[]> getValoresMuestra() {
        ArrayList<String[]> filas = new ArrayList<>();

        String nombreEstacion = this.obtenerNombreEstacion();

        for (MuestraSismica muestra : this.muestrasSismica) {
            String[] fila = muestra.getDatos(nombreEstacion);
            filas.add(fila);
        }

        return filas;
    }


    public Sismografo getSismografo() {
        return sismografo;
    }
    public void setSismografo(Sismografo sismografo) {
        this.sismografo = sismografo;
    }

    public void addMuestra(MuestraSismica nuevaMuestra) {
        this.muestrasSismica.add(nuevaMuestra);
    }

    public void removeMuestra(MuestraSismica nuevaMuestra) {
        this.muestrasSismica.remove(nuevaMuestra);
    }

    public String getCondicionAlarma() {
        return condicionAlarma;
    }
    public void setCondicionAlarma(String condicionAlarma) {
        this.condicionAlarma = condicionAlarma;
    }

    public Date getFechaHoraInicioRegistroMuestras() {
        return fechaHoraInicioRegistroMuestras;
    }

    public void setFechaHoraInicioRegistroMuestras(Date fechaHoraInicioRegistroMuestras) {
        this.fechaHoraInicioRegistroMuestras = fechaHoraInicioRegistroMuestras;
    }

    public Date getFechaHoraRegistro() {
        return fechaHoraRegistro;
    }

    public void setFechaHoraRegistro(Date fechaHoraRegistro) {
        this.fechaHoraRegistro = fechaHoraRegistro;
    }

    public Double getFrecuenciaMuestreo() {
        return frecuenciaMuestreo;
    }

    public void setFrecuenciaMuestreo(Double frecuenciaMuestreo) {
        this.frecuenciaMuestreo = frecuenciaMuestreo;
    }
}
