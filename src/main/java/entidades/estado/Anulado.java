package entidades.estado;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("ANULADO")
public class Anulado extends Estado {
    
    public Anulado() {
        this.nombreEstado = "Anulado";
    }
}
