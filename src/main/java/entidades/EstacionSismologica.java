package entidades;

import java.util.Date;

import javax.annotation.processing.Generated;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "estacion_sismologica")
public class EstacionSismologica {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "estacion_sismologica_id")
    private Integer id;

    @Column(name = "codigo_estacion")
    private String codigoEstacion;
    @Column(name = "documento_certificacion_adquisicion")
    private String documentoCertificacionAdquisicion;
    @Column(name = "fecha_solicitud_certificacion")
    private Date fechaSoliticudCertificacion;
    @Column(name = "longitud")
    private Double longitud;
    @Column(name = "latitud")
    private Double latitud;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "nro_certificacion_adquisicion")
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
