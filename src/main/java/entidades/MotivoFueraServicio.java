package entidades;

public class MotivoFueraServicio {
    private String comentario;
    private MotivoTipo motivoTipo;

    public MotivoFueraServicio(String comentario, MotivoTipo motivoTipo) {
        this.comentario = comentario;
        this.motivoTipo = motivoTipo;
    }

    public String getComentario() {
        return this.comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public MotivoTipo getMotivoTipo() {
        return this.motivoTipo;
    }
    public void setMotivoTipo(MotivoTipo motivoTipo) {
        this.motivoTipo = motivoTipo;
    }
}
