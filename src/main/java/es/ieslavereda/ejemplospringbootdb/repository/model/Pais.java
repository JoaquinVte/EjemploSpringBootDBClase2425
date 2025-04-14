package es.ieslavereda.ejemplospringbootdb.repository.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pais {
    private String nombre;
    private String url;
    private String idioma;
}
