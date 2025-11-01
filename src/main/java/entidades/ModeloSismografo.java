package entidades;

public class ModeloSismografo {
    private String caracteristicas;
    private String nombreModelo;
    private Fabricante fabricante;

    public ModeloSismografo(String caracteristicas, String nombreModelo, Fabricante fabricante) {
        this.caracteristicas = caracteristicas;
        this.nombreModelo = nombreModelo;
        this.fabricante = fabricante;
    }

    public String getCaracteristicas() {
        return this.caracteristicas;
    }
    public void setCaracteristicas(String caracteristicas) {
        this.caracteristicas = caracteristicas;
    }
    public String getNombreModelo() {
        return this.nombreModelo;
    }
    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }
    public Fabricante getFabricante() {
        return this.fabricante;
    }
    public void setFabricante(Fabricante fabricante) {
        this.fabricante = fabricante;
    }
}
