package entidades;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.annotations.ManyToAny;

import entidades.estado.Estado;
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
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "evento_sismico")
public class EventoSismico {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "evento_sismico_id")
    private Integer id;

    @Column(name = "fecha_hora_fin")
    private Date fechaHoraFin;
    @Column(name = "fecha_hora_ocurrencia")
    private Date fechaHoraOcurrencia;
    @Column(name = "latitud_epicentro")
    private double latitudEpicentro;
    @Column(name = "latitud_hipocentro")
    private double latitudHipocentro;
    @Column(name = "longitud_epicentro")
    private double longitudEpicentro;
    @Column(name = "longitud_hipocentro")
    private double longitudHipocentro;
    @Column(name = "valor_magnitud")
    private double valorMagnitud;

    @ManyToOne
    @JoinColumn(name = "clasificacion_sismo_id")
    private ClasificacionSismo clasificacion;

    @ManyToOne
    @JoinColumn(name = "magnitud_ritcher_id")
    private MagnitudRitcher magnitud;

    @ManyToOne
    @JoinColumn(name = "origen_de_generacion_id")
    private OrigenDeGeneracion origenGeneracion;

    @ManyToOne
    @JoinColumn(name = "alcance_sismo_id")
    private AlcanceSismo alcanceSismo;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estadoActual;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "evento_sismico_id")
    private List<CambioEstado> cambioEstado;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "evento_sismico_id")
    private List<SerieTemporal> seriesTemporales;

    @ManyToOne
    @JoinColumn(name = "empleado_id")
    private Empleado analistaSuperior;

    public EventoSismico() {}

    public EventoSismico(Date fechaHoraOcurrencia, double latitudEpicentro, double latitudHipocentro, double longitudEpicentro, double longitudHipocentro, double valorMagnitud, ClasificacionSismo clasificacion, Estado estadoActual, OrigenDeGeneracion origenGeneracion, AlcanceSismo alcanceSismo) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
        this.latitudEpicentro = latitudEpicentro;
        this.latitudHipocentro = latitudHipocentro;
        this.longitudEpicentro = longitudEpicentro;
        this.longitudHipocentro = longitudHipocentro;
        this.valorMagnitud = valorMagnitud;
        this.clasificacion = clasificacion;
        this.estadoActual = estadoActual;
        this.origenGeneracion = origenGeneracion;
        this.alcanceSismo = alcanceSismo;
        this.seriesTemporales = new ArrayList<>();

        this.cambioEstado = new ArrayList<>();
        this.cambioEstado.add(new CambioEstado(fechaHoraOcurrencia, estadoActual));
    }

    @Deprecated
    public void revisar(Date fecha, Estado nuevoEstado) {
        this.estadoActual = nuevoEstado;
        for (CambioEstado cambio : this.cambioEstado) {
            if (cambio.sosActual()) {
                cambio.setFechaHoraFin(fecha);
                break;
            }
        }
        this.cambioEstado.add(new CambioEstado(fecha, this.estadoActual));
    }

    public void revisar(Empleado empleadoLogueado) {
        try{this.estadoActual.revisar(empleadoLogueado, this);}
        catch(Exception e) {e.getMessage();}
    }

    @Deprecated
    public void rechazar(Date fecha, Estado nuevoEstado, Empleado responsable) {
        this.estadoActual = nuevoEstado;
        for (CambioEstado cambio : this.cambioEstado) {
            if (cambio.sosActual()) {
                cambio.setFechaHoraFin(fecha);
                break;
            }
        }
        this.cambioEstado.add(new CambioEstado(fecha, nuevoEstado, responsable));
    }

    public void rechazar(Empleado responsable) {
        try {this.estadoActual.rechazar(responsable, this);}
        catch (Exception e) {e.getMessage();}
    }

    @Deprecated
    public void confirmar(Date fecha, Estado nuevoEstado, Empleado responsable) {
        this.estadoActual = nuevoEstado;
        for (CambioEstado cambio : this.cambioEstado) {
            if (cambio.sosActual()) {
                cambio.setFechaHoraFin(fecha);
                break;
            }
        }
        this.cambioEstado.add(new CambioEstado(fecha, nuevoEstado, responsable));
    }

    public void confirmar(Empleado responsable) {
        try{this.estadoActual.confirmar(responsable, this);}
        catch(Exception e) {e.getMessage();}
    }

    @Deprecated
    public void solicitarRevision(Date fecha, Estado nuevoEstado, Empleado responsable) {
        this.estadoActual = nuevoEstado;
        for (CambioEstado cambio : this.cambioEstado) {
            if (cambio.sosActual()) {
                cambio.setFechaHoraFin(fecha);
                break;
            }
        }
        this.cambioEstado.add(new CambioEstado(fecha, nuevoEstado, responsable));
    }

    public void solicitarRevision(Empleado responsable) {
        try{this.estadoActual.solicitarRevision(responsable, this);}
        catch(Exception e){e.getMessage();}
    }

    public ArrayList<String[]> tomarInfoSeries() {
        ArrayList<String[]> filas = new ArrayList<>();

        for (SerieTemporal serie : this.seriesTemporales) {
            filas.addAll(serie.getValoresMuestra());
        }

        return filas;
    }

public boolean sosAutoDetectado() {
    boolean resultado = this.estadoActual != null && this.estadoActual.sosAutoDetectado();
    System.out.println("📊 Evento " + this.getFechaHoraOcurrencia() + 
                       " - Estado: " + (estadoActual != null ? estadoActual.getNombreEstado() : "null") + 
                       " - Es autodetectado? " + resultado);
    return resultado;
}

    public Date fechaHoraFin() {
        return this.fechaHoraFin;
    }

    public void setFechaHoraFin(Date fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public AlcanceSismo getAlcanceSismo() {
        return this.alcanceSismo;
    }

    public void setAlcanceSismo(AlcanceSismo alcanceSismo) {
        this.alcanceSismo = alcanceSismo;
    }

    public ClasificacionSismo getClasificacion() {
        return this.clasificacion;
    }

    public void setClasificacion(ClasificacionSismo clasificacion) {
        this.clasificacion = clasificacion;
    }

    public Date getFechaHoraOcurrencia() {
        return this.fechaHoraOcurrencia;
    }

    public void setFechaHoraOcurrencia(Date fechaHoraOcurrencia) {
        this.fechaHoraOcurrencia = fechaHoraOcurrencia;
    }
    public double getLatitudEpicentro() {
        return this.latitudEpicentro;
    }

    public void setlatitudEpicentro(double latitud) {
        this.latitudEpicentro = latitud;
    }

    public double getLatitudHipocentro() {
        return latitudHipocentro;
    }

    public void setLatitudHipocentro(double latitud) {
        this.latitudHipocentro = latitud;
    }

    public double getLongitudEpicentro() {
        return longitudEpicentro;
    }

    public void setLongitudEpicentro(double longitud) {
        this.longitudEpicentro = longitud;
    }

    public double getLongitudHipocentro() {
        return longitudHipocentro;
    }

    public void setLongitudHipocentro(double longitud) {
        this.longitudHipocentro = longitud;
    }

    public double getValorMagnitud() {
        return valorMagnitud;
    }

    public void setValorMagnitud(double valor) {
        this.valorMagnitud = valor;
    }

    public MagnitudRitcher getMagnitud() {
        return magnitud;
    }
    public void setMagnitud(MagnitudRitcher magnitud) {
        this.magnitud = magnitud;
    }

    public Estado getEstadoActual() {
        return estadoActual;
    }
    public void setEstadoActual(Estado estadoActual) {
        this.estadoActual = estadoActual;
    }

    public List<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }
    public void setCambioEstado(List<CambioEstado> cambioEstado) {
        this.cambioEstado = cambioEstado;
    }

    public OrigenDeGeneracion getOrigenGeneracion() {
        return origenGeneracion;
    }
    public void setOrigenGeneracion(OrigenDeGeneracion origenGeneracion) {
        this.origenGeneracion = origenGeneracion;
    }

    public Empleado getAnalistaSuperior() {
        return analistaSuperior;
    }
    public void setAnalistaSuperior(Empleado analistaSuperior) {
        this.analistaSuperior = analistaSuperior;
    }

    public List<SerieTemporal> getSeriesTemporales() {
        return seriesTemporales;
    }
    public void setSerieTemporal(ArrayList<SerieTemporal> serieTemporal) {
        this.seriesTemporales = serieTemporal;
    }

    public void addSerieTemporal(SerieTemporal serieTemporal) {
        this.seriesTemporales.add(serieTemporal);
    }
}