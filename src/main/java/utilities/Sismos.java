package utilities;

import java.util.ArrayList;
import java.util.Date;

import entidades.AlcanceSismo;
import entidades.ClasificacionSismo;
import entidades.EventoSismico;
import entidades.OrigenDeGeneracion;
import entidades.estado.Autodetectado;

public class Sismos {
    // Opción 1: si EventoSismico tiene un constructor con parámetros (ajusta tipos/valores a tu clase)
    public static ArrayList<EventoSismico> eventosSismicos = new ArrayList<EventoSismico>() {{
        add(new EventoSismico(new Date(2025-1900, 4, 2, 19, 35), 300.625, 400.355, 600.500, 600.811, 1.6, new ClasificacionSismo(61.0, 300.0, "Intermedio"), new Autodetectado(), new OrigenDeGeneracion("Sismo generado en la interplaca", "Sismo Interplaca"), new AlcanceSismo("alcance uno", "Sismo Local")));
        add(new EventoSismico(new Date(2025-1900, 3, 15, 20, 35), 500.625, 600.355, 700.500, 700.811, 1.8, new ClasificacionSismo(0.0, 60.0, "Superficial"), new Autodetectado(), new OrigenDeGeneracion("Sismo generado por la explosión de minas", "Sismo Explosión Minas"), new AlcanceSismo("alcance dos", "Sismo Regional")));
    }};

    /*
    // Opción 2: si EventoSismico tiene constructor por defecto y setters
    public static EventoSismico[] eventosSismicos = new EventoSismico[2];
    static {
        eventosSismicos[0] = new EventoSismico();
        eventosSismicos[0].setId(1);
        eventosSismicos[0].setFecha("2025-10-01T12:00");
        eventosSismicos[0].setMagnitud(5.4);

        eventosSismicos[1] = new EventoSismico();
        eventosSismicos[1].setId(2);
        eventosSismicos[1].setFecha("2025-10-02T03:30");
        eventosSismicos[1].setMagnitud(4.8);
    }
    */

}
