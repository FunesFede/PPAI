package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "motivo_fuera_servicio")
public class MotivoFueraServicio {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motivo_fuera_servicio_id")
    private Integer id;
    @Column(name = "comentario")
    private String comentario;

    @ManyToOne
    @JoinColumn(name = "motivo_tipo_id")
    private MotivoTipo motivoTipo;

    public MotivoFueraServicio() {}

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
