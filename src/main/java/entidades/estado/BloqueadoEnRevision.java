package entidades.estado;

import java.util.ArrayList;
import java.util.Date;

import entidades.CambioEstado;
import entidades.Empleado;
import entidades.EventoSismico;

public class BloqueadoEnRevision extends Estado {

    public BloqueadoEnRevision() {
        this.nombreEstado = "Bloqueado en revision";
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
        }
        Rechazado nuevoRechazado = new Rechazado();
        CambioEstado nuevoCambioEstado = new CambioEstado(fechaHora, nuevoRechazado, responsable);
        cambiosEstado.add(nuevoCambioEstado);
        eventoSismico.setCambioEstado(cambiosEstado);
    }

    @Override
    public void confirmar(Empleado responsable, EventoSismico eventoSismico) {
        Date fechaHora = obtenerFechaHoraActual();
        ArrayList<CambioEstado> cambiosEstados = eventoSismico.getCambioEstado();
        for(CambioEstado cambioEstado : cambiosEstados) {
            if (cambioEstado.sosActual()) {
                cambioEstado.setFechaHoraFin(fechaHora);
                break;
            }
        }
        Confirmado nuevoConfirmado = new Confirmado();
        CambioEstado nuevoCambioEstado = new CambioEstado(fechaHora, nuevoConfirmado);
        cambiosEstados.add(nuevoCambioEstado);
        eventoSismico.setCambioEstado(cambiosEstados);
    }

    @Override
    public void solicitarRevision(Empleado responsable, EventoSismico eventoSismico) {
        Date fechaHora = obtenerFechaHoraActual();
        ArrayList<CambioEstado> cambiosEstados = eventoSismico.getCambioEstado();
        for(CambioEstado cambioEstado : cambiosEstados) {
            if (cambioEstado.sosActual()) {
                cambioEstado.setFechaHoraFin(fechaHora);
                break;
            }
        }
        DerivadoExperto nuevoDerivadoExperto = new DerivadoExperto();
        CambioEstado nuevoCambioEstado = new CambioEstado(fechaHora, nuevoDerivadoExperto);
        cambiosEstados.add(nuevoCambioEstado);
        eventoSismico.setCambioEstado(cambiosEstados);
    }
}
