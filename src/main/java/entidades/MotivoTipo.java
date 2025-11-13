package entidades;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "motivo_tipo")
public class MotivoTipo {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "motivo_tipo_id")
    private Integer id;
    @Column(name = "descripcion")
    private String descripcion;

    public MotivoTipo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return this.descripcion;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
