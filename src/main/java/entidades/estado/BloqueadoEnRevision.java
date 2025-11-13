package entidades.estado;

import java.util.Date;
import java.util.List;

import entidades.CambioEstado;
import entidades.Empleado;
import entidades.EventoSismico;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import utilities.RepositorioDatos;

@Entity
@DiscriminatorValue("BLOQUEADO_EN_REVISION")
public class BloqueadoEnRevision extends Estado {

    public BloqueadoEnRevision() {
        this.nombreEstado = "Bloqueado en revision";
    }

    @Override
        public void rechazar(Empleado responsable, EventoSismico eventoSismico) {
        Date fechaHora = obtenerFechaHoraActual();
        List<CambioEstado> cambiosEstado = eventoSismico.getCambioEstado();
        for(CambioEstado cambioEstado : cambiosEstado ) {
            if (cambioEstado.sosActual()) {
                cambioEstado.setFechaHoraFin(fechaHora);
                break;
            }
        }
        Estado nuevoRechazado = RepositorioDatos.buscarEstadoPorNombre("Rechazado");
        CambioEstado nuevoCambioEstado = new CambioEstado(fechaHora, nuevoRechazado, responsable);
        cambiosEstado.add(nuevoCambioEstado);
        eventoSismico.setCambioEstado(cambiosEstado);
        eventoSismico.setEstadoActual(nuevoRechazado);
    }

    @Override
    public void confirmar(Empleado responsable, EventoSismico eventoSismico) {
        Date fechaHora = obtenerFechaHoraActual();
        List<CambioEstado> cambiosEstados = eventoSismico.getCambioEstado();
        for(CambioEstado cambioEstado : cambiosEstados) {
            if (cambioEstado.sosActual()) {
                cambioEstado.setFechaHoraFin(fechaHora);
                break;
            }
        }
        Estado nuevoConfirmado = RepositorioDatos.buscarEstadoPorNombre("Confirmado");
        CambioEstado nuevoCambioEstado = new CambioEstado(fechaHora, nuevoConfirmado);
        cambiosEstados.add(nuevoCambioEstado);
        eventoSismico.setCambioEstado(cambiosEstados);
        eventoSismico.setEstadoActual(nuevoConfirmado);
    }

    @Override
    public void solicitarRevision(Empleado responsable, EventoSismico eventoSismico) {
        Date fechaHora = obtenerFechaHoraActual();
        List<CambioEstado> cambiosEstados = eventoSismico.getCambioEstado();
        for(CambioEstado cambioEstado : cambiosEstados) {
            if (cambioEstado.sosActual()) {
                cambioEstado.setFechaHoraFin(fechaHora);
                break;
            }
        }
        Estado nuevoDerivadoExperto = RepositorioDatos.buscarEstadoPorNombre("Derivado experto");
        CambioEstado nuevoCambioEstado = new CambioEstado(fechaHora, nuevoDerivadoExperto);
        cambiosEstados.add(nuevoCambioEstado);
        eventoSismico.setCambioEstado(cambiosEstados);
        eventoSismico.setEstadoActual(nuevoDerivadoExperto);
    }
}
