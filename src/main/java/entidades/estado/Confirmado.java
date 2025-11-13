package entidades.estado;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CONFIRMADO")
public class Confirmado extends Estado {
    
    public Confirmado() {
        this.nombreEstado = "Confirmado";
    }
}
