package entidades.estado;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PENDIENTE_DE_CIERRE")
public class PendienteDeCierre extends Estado {
    
    public PendienteDeCierre() {
        this.nombreEstado = "Pendiente De Cierre";
    }
}
