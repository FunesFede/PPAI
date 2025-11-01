package entidades;

import java.util.ArrayList;
import java.util.Date;

public class MuestraSismica {
    private Date fechaHoraMuestra;
    private ArrayList<DetalleMuestraSismica> detalleMuestraSismica;

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
    public ArrayList<DetalleMuestraSismica> getDetalleMuestraSismica() {
        return detalleMuestraSismica;
    }
    public void removeDetalleMuestra(DetalleMuestraSismica detalleMuestraSismica) {
        this.detalleMuestraSismica.remove(detalleMuestraSismica);
    }
}
