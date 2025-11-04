package entidades.estado;

import java.util.ArrayList;
import java.util.Date;

import entidades.CambioEstado;
import entidades.Empleado;
import entidades.EventoSismico;

public class AutoDetectado extends Estado {

    public AutoDetectado() {
        this.nombreEstado = "Autodetectado";
    }

    @Override
    public void revisar(Empleado responsable, EventoSismico eventoSismico) {
        Date fechaHora = obtenerFechaHoraActual();
        ArrayList<CambioEstado> cambiosEstado = eventoSismico.getCambioEstado();
        for (CambioEstado cambioEstado : cambiosEstado) {
            if (cambioEstado.sosActual()) {
                cambioEstado.setFechaHoraFin(fechaHora);
                break;
            }
        }
        BloqueadoEnRevision nuevoBloqueadoEnRevision = new BloqueadoEnRevision();
        CambioEstado nuevoCambioEstado = new CambioEstado(fechaHora, nuevoBloqueadoEnRevision, responsable);
        cambiosEstado.add(nuevoCambioEstado);
        eventoSismico.setCambioEstado(cambiosEstado);
        eventoSismico.setEstadoActual(nuevoBloqueadoEnRevision);
    }

    @Override
    public boolean sosAutoDetectado() {
        return true;
    }
}
