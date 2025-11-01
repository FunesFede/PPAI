package entidades;

public class MagnitudRitcher {
    private String descripcionMagnitud;
    private Double numero;

    public MagnitudRitcher(String descripcionMagnitud, Double numero) {
        this.descripcionMagnitud = descripcionMagnitud;
        this.numero = numero;
    }

    public String getDescripcionMagnitud() {
        return this.descripcionMagnitud;
    }
    public void setDescripcionMagnitud(String descripcionMagnitud) {
            this.descripcionMagnitud = descripcionMagnitud;
    }

    public Double getNumero() {
        return this.numero;
    }
    public void setNumero(Double numero) {
        this.numero = numero;
    }
}
