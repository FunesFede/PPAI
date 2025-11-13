package entidades.estado;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("CERRADO")
public class Cerrado extends Estado{
    
    public Cerrado() {
        this.nombreEstado = "Cerrado";
    }
}
