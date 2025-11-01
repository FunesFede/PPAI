package entidades;

public class ClasificacionSismo {
    private Double kmProdundidadDesde;
    private Double kmProdundidadHasta;
    private String nombre;

    public ClasificacionSismo(Double kmProdundidadDesde, Double kmProdundidadHasta, String nombre) {
        this.kmProdundidadDesde = kmProdundidadDesde;
        this.kmProdundidadHasta = kmProdundidadHasta;
        this.nombre = nombre;
    }

    public Double getKmProdundidadDesde() {
        return this.kmProdundidadDesde;
    }
    public void setKmProdundidadDesde(Double kmProdundidadDesde) {
        this.kmProdundidadDesde = kmProdundidadDesde;
    }
    public Double getKmProdundidadHasta() {
        return this.kmProdundidadHasta;
    }
    public void setKmProdundidadHasta(Double kmProdundidadHasta) {
        this.kmProdundidadHasta = kmProdundidadHasta;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
