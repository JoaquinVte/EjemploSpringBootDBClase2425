package es.ieslavereda.ejemplospringbootdb.controller;

import es.ieslavereda.ejemplospringbootdb.repository.model.Cliente;
import es.ieslavereda.ejemplospringbootdb.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping(value = "clientes")
public class ClienteController {

    Logger logger = Logger.getLogger(this.getClass().getName());

    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public List<Cliente> getClientes(){
        return clienteService.getClientes();
    }

    @GetMapping(value = "{id}")
    public Cliente getCliente(@PathVariable("id") Long id){
        return clienteService.getCliente(id);
    }

    @PostMapping
    public Cliente addCliente(@RequestBody Cliente cliente){
        logger.info("Recibida peticion de añadir a " + cliente.getNombre());
        return clienteService.add(cliente);
    }


    @PutMapping
    public Cliente updateCliente(@RequestBody Cliente cliente){
        return clienteService.update(cliente);
    }


    @DeleteMapping(value = "{id}")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id){
        logger.info("Recibido eliminacion de "+id);

        try {

            return  ResponseEntity.ok( clienteService.remove(id));

        } catch (SQLException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
