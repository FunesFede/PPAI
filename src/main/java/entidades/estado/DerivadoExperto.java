package entidades.estado;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("DERIVADO_EXPERTO")
public class DerivadoExperto extends Estado {
    
    public DerivadoExperto () {
        this.nombreEstado = "Derivado a experto";
    }
}
