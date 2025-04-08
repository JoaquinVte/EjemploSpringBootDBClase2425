package es.ieslavereda.ejemplospringbootdb.controller;

import es.ieslavereda.ejemplospringbootdb.repository.Cliente;
import es.ieslavereda.ejemplospringbootdb.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "clientes")
public class ClienteController {

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
        return clienteService.add(cliente);
    }

    @DeleteMapping(value = "{id}")
    public Cliente deleteCliente(@PathVariable Long id){
        return clienteService.remove(id);
    }


}
