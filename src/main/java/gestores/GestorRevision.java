package gestores;

import controllers.InterfazSismos;
import entidades.*;
import entidades.estado.Autodetectado;
import entidades.estado.Estado;

import java.util.ArrayList;
import java.util.Date;

public class GestorRevision {
    private Sesion sesionActual;
    private ArrayList<Estado> estados;
    private ArrayList<EventoSismico> eventosSismicos;
    private ArrayList<EstacionSismologica> estacionesSismologicas;
    private ArrayList<Sismografo> sismografos;
    private ArrayList<SerieTemporal> seriesTemporales;
    private InterfazSismos interfaz;

    private EventoSismico eventoSismicoSeleccionado;

    public GestorRevision(InterfazSismos interfaz) {
        this.interfaz = interfaz;

        sesionActual = new Sesion(new Date(), new Usuario("responsable1", "123456", new Empleado("Perez", "Juan", "juanperez@mail.com", "456789", new Rol("Responsable de revisiones de eventos sismicos", "Analista Sismos"))));

        estacionesSismologicas = new ArrayList<>();
        estacionesSismologicas.add(new EstacionSismologica("E1", "Estacion 1", 300.600, 700.500));
        estacionesSismologicas.add(new EstacionSismologica("E2", "Estacion 2", 300.600, 700.500));

        sismografos = new ArrayList<>();
        sismografos.add(new Sismografo(new Date(2015-1900, 0, 15), "S1", "12345678910", estacionesSismologicas.getFirst()));
        sismografos.add(new Sismografo(new Date(2015-1900, 0, 15), "S2", "12345678910", estacionesSismologicas.get(1)));

        seriesTemporales = new ArrayList<>();
        seriesTemporales.add(new SerieTemporal(sismografos.getFirst()));
        seriesTemporales.add(new SerieTemporal(sismografos.get(1)));

        eventosSismicos = new ArrayList<>();

        EventoSismico evento1 = new EventoSismico(new Date(2025-1900, 4, 2, 19, 35), 300.625, 400.355, 600.500, 600.811, 1.6, new ClasificacionSismo(61.0, 300.0, "Intermedio"), new Autodetectado(), new OrigenDeGeneracion("Sismo generado en la interplaca", "Sismo Interplaca"), new AlcanceSismo("alcance uno", "Sismo Local"));
        EventoSismico evento2 = new EventoSismico(new Date(2025-1900, 3, 15, 20, 35), 500.625, 600.355, 700.500, 700.811, 1.8, new ClasificacionSismo(0.0, 60.0, "Superficial"), new Autodetectado(), new OrigenDeGeneracion("Sismo generado por la explosión de minas", "Sismo Explosión Minas"), new AlcanceSismo("alcance dos", "Sismo Regional"));

        evento1.addSerieTemporal(seriesTemporales.getFirst());
        evento1.addSerieTemporal(seriesTemporales.get(1));

        evento2.addSerieTemporal(seriesTemporales.getFirst());
        evento2.addSerieTemporal(seriesTemporales.get(1));

        eventosSismicos.add(evento1);
        eventosSismicos.add(evento2);
    }

    public void nuevaRevision() {
        ArrayList<EventoSismico> eventosSismicosAutoDetectados = buscarEventoSismicoAutoDetectado();
        ArrayList<EventoSismico> eventoSismicosAutoDetectadoOrdenados = ordenarPorFechaOcurrencia(eventosSismicosAutoDetectados);

        ArrayList<String[]> datosEventosAutoDetectados = new ArrayList<>();
        for (EventoSismico evento : eventoSismicosAutoDetectadoOrdenados) {
            String coordenadasEpicentro = "(" + evento.getLatitudEpicentro() + ", " + evento.getLatitudEpicentro() + ")";
            String coordenadasHipocentro = "(" + evento.getLatitudHipocentro() + ", " + evento.getLongitudHipocentro() + ")";
            datosEventosAutoDetectados.add(new String[] {evento.getFechaHoraOcurrencia().toString(), coordenadasEpicentro, coordenadasHipocentro, Double.toString(evento.getValorMagnitud())});
        }
        this.interfaz.pedirSeleccionEvento(datosEventosAutoDetectados);
    }

    private ArrayList<EventoSismico> buscarEventoSismicoAutoDetectado() {
        ArrayList<EventoSismico> eventosSismicosAutoDetectados = new ArrayList<>();
        System.out.println("Buscando eventos sismicos auto detectados " + this.eventosSismicos.toString());
        for (EventoSismico evento : this.eventosSismicos) {
            //System.out.println(evento.getEstadoActual().sosAutoDetectado());
            if (evento.sosAutoDetectado()) {
                eventosSismicosAutoDetectados.add(evento);
            }
        }
        return eventosSismicosAutoDetectados;
    }

    private ArrayList<EventoSismico> ordenarPorFechaOcurrencia(ArrayList<EventoSismico> eventosSismicos) {
        eventosSismicos.sort((evento1, evento2) -> evento1.getFechaHoraOcurrencia().compareTo(evento2.getFechaHoraOcurrencia()));
        return eventosSismicos;
    }

    private Date getFechaHoraActual() {
        return new Date();
    }

    // private Estado buscarBloqueadoEnRevision() {
    //     Estado estadoBloqueadoEnRevision = null;
    //     for (Estado estado: this.estados) {
    //         if (estado.sosAmbitoEventoSismico()) {
    //             if (estado.sosBloqueadoEnRevision()) {
    //                 estadoBloqueadoEnRevision = estado;
    //             }
    //         }
    //     }
    //     return estadoBloqueadoEnRevision;
    // }

    // @Deprecated
    // private void bloquearEventoSismico() {
    //     Date fechaHoraActual = this.getFechaHoraActual();
    //     Estado estadoBloqueadoEnRevision = this.buscarBloqueadoEnRevision();
    //     this.eventoSismicoSeleccionado.revisar(fechaHoraActual, estadoBloqueadoEnRevision);
    // }

    private void bloquearEventoSismico() {
        Empleado empleadoLogueado = this.obtenerAnalistaSismosLogueado();
        this.eventoSismicoSeleccionado.revisar(empleadoLogueado);
    }

    private String tomarAlcance() {
        return this.eventoSismicoSeleccionado.getAlcanceSismo().getNombre();
    }

    private String tomarClasificacion() {
        return this.eventoSismicoSeleccionado.getClasificacion().getNombre();
    }

    private String tomarOrigen() {
        return this.eventoSismicoSeleccionado.getOrigenGeneracion().getNombre();
    }

    private ArrayList<String[]> tomarInfoSeries() {
        return this.eventoSismicoSeleccionado.tomarInfoSeries();
    }

    private String hacerSismograma() {
        System.out.println("Haciendo sismograma");
        this.interfaz.mostrarSismograma();
        return "Sismograma generado correctamente";
    }

    public void tomarEventoSeleccionado(String[] datosEvento) {
        try {
            this.eventoSismicoSeleccionado = this.eventosSismicos.stream()
                    .filter(e -> e.getFechaHoraOcurrencia().toString().equals(datosEvento[0]))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("Evento no encontrado"));
            
            this.bloquearEventoSismico();

            // Obtener los datos necesarios
            String alcance = this.tomarAlcance();
            String clasificacion = this.tomarClasificacion();
            String origen = this.tomarOrigen();
            ArrayList<String[]> infoSeries = this.tomarInfoSeries();
            
            // Mostrar los datos en la nueva vista
            this.interfaz.mostrarDatosEventoSismico(alcance, clasificacion, origen, infoSeries);

            this.hacerSismograma();

            this.interfaz.habilitarOpcionVisualizarMapa();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void tomarNoVerMapa() {
        this.interfaz.solicitarOpcionModificacion();
    }

    public void tomarOpcionNoModificar() {
        this.interfaz.solicitarAccion();
    }

    private Empleado obtenerAnalistaSismosLogueado() {
        return this.sesionActual.obtenerAnalistaSismosLogueado();
    }

    // private Estado buscarEstadoRechazado() {
    //     Estado estadoRechazado = null;
    //     for (Estado estado: this.estados) {
    //         if (estado.sosAmbitoEventoSismico()) {
    //             if (estado.sosRechazado()) {
    //                 estadoRechazado = estado;
    //             }
    //         }
    //     }
    //     return estadoRechazado;
    // }

    private boolean validarExistenciaDatos() {
        return this.eventoSismicoSeleccionado != null && this.eventoSismicoSeleccionado.getValorMagnitud() != 0 && this.eventoSismicoSeleccionado.getAlcanceSismo() != null && this.eventoSismicoSeleccionado.getOrigenGeneracion() != null;
    }

    // @Deprecated
    // private void rechazarEvento() {
    //     Date fechaHoraActual = this.getFechaHoraActual();
    //     Estado estadoRechazado = this.buscarEstadoRechazado();
    //     System.out.println("Estado rechazado: " + estadoRechazado.getNombreEstado());
    //     Empleado responsable = this.obtenerAnalistaSismosLogueado();

    //     System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());

    //     this.eventoSismicoSeleccionado.rechazar(fechaHoraActual, estadoRechazado, responsable);
    //     System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());
    // }

    private void rechazarEvento() {
        Empleado responsable = this.obtenerAnalistaSismosLogueado();
        this.eventoSismicoSeleccionado.rechazar(responsable);
    }

    public boolean tomarAccionRechazarEvento() {
        if (this.validarExistenciaDatos()) {
            this.rechazarEvento();
            return true;
        } else {
            return false;
        }
    }

    // @Deprecated
    // private void confirmarEvento() {
    //     Date fechaHoraActual = this.getFechaHoraActual();
    //     Estado estadoConfirmado = this.buscarEstadoConfirmado();

    //     Empleado responsable = this.obtenerAnalistaSismosLogueado();

    //     System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());

    //     this.eventoSismicoSeleccionado.confirmar(fechaHoraActual, estadoConfirmado, responsable);
    //     System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());
    // }

    private void confirmarEvento() {
        Empleado responsable = this.obtenerAnalistaSismosLogueado();
        this.eventoSismicoSeleccionado.confirmar(responsable);
    }

    // private Estado buscarEstadoConfirmado() {
    //     Estado estadoConfirmado = null;
    //     for (Estado estado: this.estados) {
    //         if (estado.sosAmbitoEventoSismico()) {
    //             if (estado.sosConfirmado()) {
    //                 estadoConfirmado = estado;
    //             }
    //         }
    //     }
    //     return estadoConfirmado;
    // }

    public boolean tomarAccionConfirmar() {
        if (this.validarExistenciaDatos()) {
            this.confirmarEvento();
            return true;
        } else {
            return false;
        }
    }

    public boolean tomarAccionSolicitarRevision() {
        if (this.validarExistenciaDatos()) {
            this.solicitarRevisionEvento();
            return true;
        } else {
            return false;
        }
    }

    // @Deprecated
    // private void solicitarRevisionEvento() {
    //     Date fechaHoraActual = this.getFechaHoraActual();
    //     Estado estadoDerivadoAExperto = this.buscarEstadoDerivadoAExperto();

    //     Empleado responsable = this.obtenerAnalistaSismosLogueado();

    //     System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());

    //     this.eventoSismicoSeleccionado.solicitarRevision(fechaHoraActual, estadoDerivadoAExperto, responsable);
    //     System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());
    // }

    public void solicitarRevisionEvento() {
        Empleado responsable = this.obtenerAnalistaSismosLogueado();
        this.eventoSismicoSeleccionado.solicitarRevision(responsable);
    }



//     private Estado buscarEstadoDerivadoAExperto() {
//         Estado estadoDerivadoAExperto = null;
//         for (Estado estado: this.estados) {
//             if (estado.sosAmbitoEventoSismico()) {
//                 if (estado.sosDerivadoAExperto()) {
//                     estadoDerivadoAExperto = estado;
//                 }
//             }
//         }
//         return estadoDerivadoAExperto;
//     }

}