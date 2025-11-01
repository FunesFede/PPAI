package entidades;

import java.util.ArrayList;
import java.util.Date;

public class SerieTemporal {
    private String condicionAlarma;
    private Date fechaHoraInicioRegistroMuestras;
    private Date fechaHoraRegistro;
    private Double frecuenciaMuestreo;
    private ArrayList<MuestraSismica> muestrasSismica;
    private Sismografo sismografo;

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
