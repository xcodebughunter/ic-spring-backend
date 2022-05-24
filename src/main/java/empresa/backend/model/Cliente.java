package empresa.backend.model;



import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "clientes")
@Data
public class Cliente {

    @Id private String id;

    private String nombre;

    @Indexed(unique = true)
    private String nit;
    private String direccion;
    private String telefono;
    private String email;
    private String observaciones;
    private Boolean activo;


    @CreatedDate
    private LocalDateTime fechaCreacion;

    @Version
    private Long version;

    public Cliente(){}

    public Cliente(String id, String nombre, String nit, String direccion, String telefono, String email, String observaciones, Boolean activo) {
        this.id = id;
        this.nombre = nombre;
        this.nit = nit;
        this.direccion = direccion;
        this.telefono = telefono;
        this.email = email;
        this.observaciones = observaciones;
        this.activo = activo;
    }
}
