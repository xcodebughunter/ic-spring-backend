package empresa.backend.model;


import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Version;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Document
@Data
public class Vehiculo {

    @Id
    private String id;
    private String marca;
    private String modelo;

    @Indexed(unique = true)
    private String placas;

    private double kilometrajeInicial;
    private String cliente;
    private String observaciones;

    @CreatedDate
    private LocalDateTime fechaCreacion;

    @Version
    private Long version;

}
