package entidades.estado;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PENDIENTE_DE_REVISION")
public class PendienteDeRevision extends Estado {
    
    public PendienteDeRevision() {
        this.nombreEstado = "Pendiente De Revision";
    }
}
