package entidades.estado;

import java.util.ArrayList;
import java.util.Date;

import entidades.CambioEstado;
import entidades.Empleado;
import entidades.EventoSismico;

public class BloqueadoEnRevision extends Estado {

    public BloqueadoEnRevision(String ambito, String nombreEstado) {
        super(ambito, nombreEstado);
    }

    @Override
        public void rechazar(Empleado responsable, EventoSismico eventoSismico) {
        Date fechaHora = obtenerFechaHoraActual();
        ArrayList<CambioEstado> cambiosEstado = eventoSismico.getCambioEstado();
        for(CambioEstado cambioEstado : cambiosEstado ) {
            if (cambioEstado.sosActual()) {
                cambioEstado.setFechaHoraFin(fechaHora);
                break;
            }
            Rechazado nuevoRechazado = new Rechazado(this.getAmbito(), "rechazado");
            CambioEstado nuevoCambioEstado = new CambioEstado(fechaHora, nuevoRechazado, responsable);
            cambiosEstado.add(nuevoCambioEstado);
            eventoSismico.setCambioEstado(cambiosEstado);
        }
        

    }
}
