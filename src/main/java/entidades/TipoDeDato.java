package entidades;

public class TipoDeDato {
    private String denominacion;
    private String nombreUnidadMedida;
    private Double valorUmbral;

    public TipoDeDato(String denominacion, String nombreUnidadMedida, Double valorUmbral) {
        this.denominacion = denominacion;
        this.nombreUnidadMedida = nombreUnidadMedida;
        this.valorUmbral = valorUmbral;
    }

    public boolean esTuDenominacion(String denominacion) {
        return this.denominacion.equals(denominacion);
    }

    public String getDenominacion() {
        return denominacion;
    }

    public void setDenominacion(String denominacion) {
        this.denominacion = denominacion;
    }

    public String getNombreUnidadMedida() {
        return nombreUnidadMedida;
    }
    public void setNombreUnidadMedida(String nombreUnidadMedida) {
        this.nombreUnidadMedida = nombreUnidadMedida;
    }
    public Double getValorUmbral() {
        return valorUmbral;
    }
    public void setValorUmbral(Double valorUmbral) {
        this.valorUmbral = valorUmbral;
    }
}
