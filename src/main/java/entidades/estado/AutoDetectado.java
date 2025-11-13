package entidades.estado;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import entidades.CambioEstado;
import entidades.Empleado;
import entidades.EventoSismico;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import utilities.RepositorioDatos;

@Entity
@DiscriminatorValue("AUTO_DETECTADO")
public class AutoDetectado extends Estado {

    public AutoDetectado() {
        this.nombreEstado = "Autodetectado";
    }

    @Override
    public void revisar(Empleado responsable, EventoSismico eventoSismico) {
        Date fechaHora = obtenerFechaHoraActual();
        List<CambioEstado> cambiosEstado = eventoSismico.getCambioEstado();
        for (CambioEstado cambioEstado : cambiosEstado) {
            if (cambioEstado.sosActual()) {
                cambioEstado.setFechaHoraFin(fechaHora);
                break;
            }
        }
        Estado nuevoBloqueadoEnRevision = RepositorioDatos.buscarEstadoPorNombre("Bloqueado en revision");
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
