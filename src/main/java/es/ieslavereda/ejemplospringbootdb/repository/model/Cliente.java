package es.ieslavereda.ejemplospringbootdb.repository.model;

import lombok.*;

import java.sql.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Cliente {

    private Long id;
    private String dni;
    private String nombre;
    private String apellidos;
    private Date fechaNacimiento;
    private String imagen;

}
