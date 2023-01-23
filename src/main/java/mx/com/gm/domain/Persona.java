package mx.com.gm.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
@Entity
@Table(name = "persona")
public class Persona {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPersona;
    
    @NotEmpty
    private String identificacion;
    
    @NotEmpty
    private String nombre;
    
    @NotEmpty
    private String apellido;
    
    //@NotEmpty
    private Date fecha;
    
    @NotEmpty
    private String piso;
    
    private String telefono;
    
    
    
}
