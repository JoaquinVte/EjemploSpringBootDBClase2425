package es.ieslavereda.ejemplospringbootdb.controller;


import es.ieslavereda.ejemplospringbootdb.repository.model.Pais;
import es.ieslavereda.ejemplospringbootdb.service.PaisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping(value = "pais")
public class PaisController {

    @Autowired
    private PaisService paisService;


    @PostMapping
    public ResponseEntity<?> crearPais(@RequestBody Pais pais){
        try {
            return new ResponseEntity<>(paisService.crear(pais),HttpStatus.OK);
        } catch (SQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "{nombre}")
    public ResponseEntity<?> obtenerPais(@PathVariable("nombre") String nombre){

        try {
            return new ResponseEntity<>(paisService.obtener(nombre),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping()
    public ResponseEntity<?> obtenerPaises(){

        try {
            return new ResponseEntity<>(paisService.getAll(),HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }



}
