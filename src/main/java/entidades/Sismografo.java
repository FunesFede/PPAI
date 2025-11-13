package entidades;

import java.util.ArrayList;
import java.util.Date;

import entidades.estado.Estado;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sismografo")
@Data
public class Sismografo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sismografo_id")
    private Integer id;

    @Column(name = "fecha_adquisicion")
    private Date fechaAdquisicion;
    @Column(name = "identificador_sismografo")
    private String identificadorSismografo;
    @Column(name = "nro_serie")
    private String nroSerie;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estadoActual;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sismografo_id")
    private ArrayList<CambioEstado> cambioEstado;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "sismografo_id")
    private ArrayList<SerieTemporal> serieTemporal;

    @ManyToOne
    @JoinColumn(name = "modelo_sismografo_id")
    private ModeloSismografo modeloSismografo;

    @ManyToOne
    @JoinColumn(name = "estacion_sismologica_id")
    private EstacionSismologica estacionSismologica;
    // private Reparacion reparacion;

    public  Sismografo(Date fechaAdquisicion, String identificadorSismografo, String nroSerie, EstacionSismologica estacionSismologica) {
        this.fechaAdquisicion = fechaAdquisicion;
        this.identificadorSismografo = identificadorSismografo;
        this.nroSerie = nroSerie;
        this.cambioEstado = new ArrayList<>();
        this.serieTemporal = new ArrayList<>();
        this.estacionSismologica = estacionSismologica;
    }

    public String obtenerNombreEstacion() {
        return this.estacionSismologica.getNombre();
    }

    public Date getFechaAdquisicion() {
        return fechaAdquisicion;
    }
    public void setFechaAdquisicion(Date fechaAdquisicion) {
        this.fechaAdquisicion = fechaAdquisicion;
    }

    public String getIdentificadorSismografo() {
        return identificadorSismografo;
    }
    public void setIdentificadorSismografo(String identificadorSismografo) {
        this.identificadorSismografo = identificadorSismografo;
    }

    public String getNroSerie() {
        return nroSerie;
    }
    public void setNroSerie(String nroSerie) {
        this.nroSerie = nroSerie;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }
    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public ArrayList<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }
    public void addCambioEstado(CambioEstado cambioEstado) {
        this.cambioEstado.add(cambioEstado);
    }
    public void removeCambioEstado(CambioEstado cambioEstado) {
        this.cambioEstado.remove(cambioEstado);
    }

    public ModeloSismografo getModeloSismografo() {
        return modeloSismografo;
    }
    public void setModeloSismografo(ModeloSismografo modeloSismografo) {
        this.modeloSismografo = modeloSismografo;
    }

    public ArrayList<SerieTemporal> getSerieTemporal() {
        return serieTemporal;
    }
    public void addSerieTemporal(SerieTemporal nuevaSerieTemporal) {
        this.serieTemporal.add(nuevaSerieTemporal);
    }
    public void removeSerieTemporal(SerieTemporal nuevaSerieTemporal) {
        this.serieTemporal.remove(nuevaSerieTemporal);
    }

}
