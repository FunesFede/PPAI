package entidades.estado;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RECHAZADO")
public class Rechazado extends Estado {
    
    public Rechazado() {
        this.nombreEstado = "Rechazado";
    }
}
