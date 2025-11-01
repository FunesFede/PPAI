package entidades;

import java.util.ArrayList;
import java.util.Date;

import entidades.estado.Estado;

public class EventoSismico {
    private Date fechaHoraFin;
    private Date fechaHoraOcurrencia;
    private double latitudEpicentro;
    private double latitudHipocentro;
    private double longitudEpicentro;
    private double longitudHipocentro;
    private double valorMagnitud;

    private ClasificacionSismo clasificacion;
    private MagnitudRitcher magnitud;
    private OrigenDeGeneracion origenGeneracion;
    private AlcanceSismo alcanceSismo;
    private Estado estadoActual;
    private ArrayList<CambioEstado> cambioEstado;
    private ArrayList<SerieTemporal> seriesTemporales;
    private Empleado analistaSuperior;

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

    public ArrayList<String[]> tomarInfoSeries() {
        ArrayList<String[]> filas = new ArrayList<>();

        for (SerieTemporal serie : this.seriesTemporales) {
            filas.addAll(serie.getValoresMuestra());
        }

        return filas;
    }

    public boolean sosAutoDetectado() {
        return this.estadoActual.sosAutoDetectado();
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

    public ArrayList<CambioEstado> getCambioEstado() {
        return cambioEstado;
    }
    public void setCambioEstado(ArrayList<CambioEstado> cambioEstado) {
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

    public ArrayList<SerieTemporal> getSeriesTemporales() {
        return seriesTemporales;
    }
    public void setSerieTemporal(ArrayList<SerieTemporal> serieTemporal) {
        this.seriesTemporales = serieTemporal;
    }

    public void addSerieTemporal(SerieTemporal serieTemporal) {
        this.seriesTemporales.add(serieTemporal);
    }
}