package gestores;

import controllers.InterfazSismos;
import entidades.*;
import utilities.RepositorioDatos;

import java.util.ArrayList;
import java.util.Date;

public class GestorRevision {
    private Sesion sesionActual;
    // private ArrayList<Estado> estados;
    private ArrayList<EventoSismico> eventosSismicos;
    private ArrayList<EstacionSismologica> estacionesSismologicas;
    private ArrayList<Sismografo> sismografos;
    private ArrayList<SerieTemporal> seriesTemporales;
    private InterfazSismos interfaz;
    private EventoSismico eventoSismicoSeleccionado;

    public GestorRevision(InterfazSismos interfaz) {
        this.interfaz = interfaz;
        
        // Cargar datos desde la base de datos
        cargarDatosDesdeBaseDeDatos();
    }
    
    private void cargarDatosDesdeBaseDeDatos() {
        // Cargar la sesión activa (última sesión sin fecha_hora_fin)
        this.sesionActual = RepositorioDatos.cargarSesionActiva();
        
        if (this.sesionActual != null) {
            System.out.println("✓ Sesión iniciada: Usuario '" + 
                this.sesionActual.getUsuario().getNombreUsuario() + 
                "' (" + this.sesionActual.getUsuario().getEmpleado().getNombre() + " " +
                this.sesionActual.getUsuario().getEmpleado().getApellido() + ")");
        } else {
            System.out.println("⚠ No hay sesión activa - creando sesión de prueba...");
            // Ya no pasa, le agrege una sesion en los datos
            crearSesionPrueba();
        }
        
        // Cargar catálogos y datos principales
        this.estacionesSismologicas = new ArrayList<>(
            RepositorioDatos.cargarEstacionesSismologicas()
        );
        
        this.sismografos = new ArrayList<>(
            RepositorioDatos.cargarSismografos()
        );
        
        this.seriesTemporales = new ArrayList<>(
            RepositorioDatos.cargarSeriesTemporales()
        );
        
        this.eventosSismicos = new ArrayList<>(
            RepositorioDatos.cargarEventosSismicos()
        );
        
        System.out.println("✓ Datos cargados: " + 
            eventosSismicos.size() + " eventos sísmicos, " +
            estacionesSismologicas.size() + " estaciones, " +
            sismografos.size() + " sismógrafos");
    }

    private void crearSesionPrueba() {
        Usuario usuarioPrueba = RepositorioDatos.cargarUsuarioPorNombre("mgonzalez");
        if (usuarioPrueba != null) {
            this.sesionActual = new Sesion(new Date(), usuarioPrueba);
            System.out.println("✓ Sesión de prueba creada para: " + 
                usuarioPrueba.getEmpleado().getNombre() + " " +
                usuarioPrueba.getEmpleado().getApellido());
        }
    }

    public void nuevaRevision() {
        ArrayList<EventoSismico> eventosSismicosAutoDetectados = buscarEventoSismicoAutoDetectado();
        ArrayList<EventoSismico> eventoSismicosAutoDetectadoOrdenados = ordenarPorFechaOcurrencia(
                eventosSismicosAutoDetectados);

        ArrayList<String[]> datosEventosAutoDetectados = new ArrayList<>();
        for (EventoSismico evento : eventoSismicosAutoDetectadoOrdenados) {
            String coordenadasEpicentro = "(" + evento.getLatitudEpicentro() + ", " + evento.getLatitudEpicentro()
                    + ")";
            String coordenadasHipocentro = "(" + evento.getLatitudHipocentro() + ", " + evento.getLongitudHipocentro()
                    + ")";
            datosEventosAutoDetectados.add(new String[] { evento.getFechaHoraOcurrencia().toString(),
                    coordenadasEpicentro, coordenadasHipocentro, Double.toString(evento.getValorMagnitud()) });
        }
        this.interfaz.pedirSeleccionEvento(datosEventosAutoDetectados);
    }

    private ArrayList<EventoSismico> buscarEventoSismicoAutoDetectado() {
        ArrayList<EventoSismico> eventosSismicosAutoDetectados = new ArrayList<>();
        for (EventoSismico evento : this.eventosSismicos) {
            // System.out.println(evento.getEstadoActual().sosAutoDetectado());
            if (evento.sosAutoDetectado()) {
                eventosSismicosAutoDetectados.add(evento);
            }
        }
        return eventosSismicosAutoDetectados;
    }

    private ArrayList<EventoSismico> ordenarPorFechaOcurrencia(ArrayList<EventoSismico> eventosSismicos) {
        eventosSismicos.sort(
                (evento1, evento2) -> evento1.getFechaHoraOcurrencia().compareTo(evento2.getFechaHoraOcurrencia()));
        return eventosSismicos;
    }

    // private Date getFechaHoraActual() {
    //     return new Date();
    // }

    // private Estado buscarBloqueadoEnRevision() {
    // Estado estadoBloqueadoEnRevision = null;
    // for (Estado estado: this.estados) {
    // if (estado.sosAmbitoEventoSismico()) {
    // if (estado.sosBloqueadoEnRevision()) {
    // estadoBloqueadoEnRevision = estado;
    // }
    // }
    // }
    // return estadoBloqueadoEnRevision;
    // }

    // @Deprecated
    // private void bloquearEventoSismico() {
    // Date fechaHoraActual = this.getFechaHoraActual();
    // Estado estadoBloqueadoEnRevision = this.buscarBloqueadoEnRevision();
    // this.eventoSismicoSeleccionado.revisar(fechaHoraActual,
    // estadoBloqueadoEnRevision);
    // }

    private void bloquearEventoSismico() {
        Empleado empleadoLogueado = this.obtenerAnalistaSismosLogueado();
        this.eventoSismicoSeleccionado.revisar(empleadoLogueado);
        RepositorioDatos.actualizar(this.eventoSismicoSeleccionado);
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
        if (this.sesionActual == null) {
            throw new IllegalStateException("No hay sesión activa");
        }
        return this.sesionActual.obtenerAnalistaSismosLogueado();
    }

    // private Estado buscarEstadoRechazado() {
    // Estado estadoRechazado = null;
    // for (Estado estado: this.estados) {
    // if (estado.sosAmbitoEventoSismico()) {
    // if (estado.sosRechazado()) {
    // estadoRechazado = estado;
    // }
    // }
    // }
    // return estadoRechazado;
    // }

    private boolean validarExistenciaDatos() {
        return this.eventoSismicoSeleccionado != null && this.eventoSismicoSeleccionado.getValorMagnitud() != 0
                && this.eventoSismicoSeleccionado.getAlcanceSismo() != null
                && this.eventoSismicoSeleccionado.getOrigenGeneracion() != null;
    }

    // @Deprecated
    // private void rechazarEvento() {
    // Date fechaHoraActual = this.getFechaHoraActual();
    // Estado estadoRechazado = this.buscarEstadoRechazado();
    // System.out.println("Estado rechazado: " + estadoRechazado.getNombreEstado());
    // Empleado responsable = this.obtenerAnalistaSismosLogueado();

    // System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());

    // this.eventoSismicoSeleccionado.rechazar(fechaHoraActual, estadoRechazado,
    // responsable);
    // System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());
    // }

    private void rechazarEvento() {
        Empleado responsable = this.obtenerAnalistaSismosLogueado();
        this.eventoSismicoSeleccionado.rechazar(responsable);
        RepositorioDatos.actualizar(this.eventoSismicoSeleccionado);
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
    // Date fechaHoraActual = this.getFechaHoraActual();
    // Estado estadoConfirmado = this.buscarEstadoConfirmado();

    // Empleado responsable = this.obtenerAnalistaSismosLogueado();

    // System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());

    // this.eventoSismicoSeleccionado.confirmar(fechaHoraActual, estadoConfirmado,
    // responsable);
    // System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());
    // }

    private void confirmarEvento() {
        Empleado responsable = this.obtenerAnalistaSismosLogueado();
        this.eventoSismicoSeleccionado.confirmar(responsable);
        RepositorioDatos.actualizar(this.eventoSismicoSeleccionado);
    }

    // private Estado buscarEstadoConfirmado() {
    // Estado estadoConfirmado = null;
    // for (Estado estado: this.estados) {
    // if (estado.sosAmbitoEventoSismico()) {
    // if (estado.sosConfirmado()) {
    // estadoConfirmado = estado;
    // }
    // }
    // }
    // return estadoConfirmado;
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
    // Date fechaHoraActual = this.getFechaHoraActual();
    // Estado estadoDerivadoAExperto = this.buscarEstadoDerivadoAExperto();

    // Empleado responsable = this.obtenerAnalistaSismosLogueado();

    // System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());

    // this.eventoSismicoSeleccionado.solicitarRevision(fechaHoraActual,
    // estadoDerivadoAExperto, responsable);
    // System.out.println(this.eventoSismicoSeleccionado.getEstadoActual().getNombreEstado());
    // }

    public void solicitarRevisionEvento() {
        Empleado responsable = this.obtenerAnalistaSismosLogueado();
        this.eventoSismicoSeleccionado.solicitarRevision(responsable);
        RepositorioDatos.actualizar(this.eventoSismicoSeleccionado);
    }

    // private Estado buscarEstadoDerivadoAExperto() {
    // Estado estadoDerivadoAExperto = null;
    // for (Estado estado: this.estados) {
    // if (estado.sosAmbitoEventoSismico()) {
    // if (estado.sosDerivadoAExperto()) {
    // estadoDerivadoAExperto = estado;
    // }
    // }
    // }
    // return estadoDerivadoAExperto;
    // }

}