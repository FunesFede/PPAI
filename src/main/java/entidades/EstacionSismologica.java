package entidades;

import java.util.Date;

public class EstacionSismologica {
    private String codigoEstacion;
    private String documentoCertificacionAdquisicion;
    private Date fechaSoliticudCertificacion;
    private Double longitud;
    private Double latitud;
    private String nombre;
    private String nroCertificacionAdquisicion;

    public EstacionSismologica(String codigoEstacion, String nombre, Double longitud, Double latitud) {
        this.codigoEstacion = codigoEstacion;
        this.nombre = nombre;
        this.longitud = longitud;
        this.latitud = latitud;
    }

    public String getCodigoEstacion() {
        return this.codigoEstacion;
    }
    public void setCodigoEstacion(String codigoEstacion) {
        this.codigoEstacion = codigoEstacion;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public Double getLongitud() {
        return this.longitud;
    }
    public void setLongitud(Double longitud) {
        this.longitud = longitud;
    }
    public Double getLatitud() {
        return this.latitud;
    }
    public void setLatitud(Double latitud) {
        this.latitud = latitud;
    }
    public String getNroCertificacionAdquisicion() {
        return nroCertificacionAdquisicion;
    }
    public void setNroCertificacionAdquisicion(String nroCertificacionAdquisicion) {
        this.nroCertificacionAdquisicion = nroCertificacionAdquisicion;
    }
    public String getDocumentoCertificacionAdquisicion() {
        return documentoCertificacionAdquisicion;
    }
    public void setDocumentoCertificacionAdquisicion(String documentoCertificacionAdquisicion) {
        this.documentoCertificacionAdquisicion = documentoCertificacionAdquisicion;
    }
    public Date getFechaSoliticudCertificacion() {
        return fechaSoliticudCertificacion;
    }
    public void setFechaSoliticudCertificacion(Date fechaSoliticudCertificacion) {
        this.fechaSoliticudCertificacion = fechaSoliticudCertificacion;
    }
}
