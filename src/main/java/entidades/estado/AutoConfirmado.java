package entidades.estado;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AUTO_CONFIRMADO")
public class AutoConfirmado extends Estado {
    
    public AutoConfirmado() {
        this.nombreEstado = "Auto Confirmado";
    }
}
