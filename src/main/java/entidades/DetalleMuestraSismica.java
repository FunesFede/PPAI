package entidades;

public class DetalleMuestraSismica {
    private Double valor;
    private TipoDeDato tipoDeDato;

    public DetalleMuestraSismica(Double valor, TipoDeDato tipoDeDato) {
        this.valor = valor;
        this.tipoDeDato = tipoDeDato;
    }
    public String getDatos() {
        return this.valor + this.tipoDeDato.getNombreUnidadMedida();
    }

    public Double getValor() {
        return this.valor;
    }
    public void setValor(Double valor) {
        this.valor = valor;
    }
    public TipoDeDato getTipoDeDato() {
        return this.tipoDeDato;
    }
    public void setTipoDeDato(TipoDeDato tipoDeDato) {
        this.tipoDeDato = tipoDeDato;
    }
}
