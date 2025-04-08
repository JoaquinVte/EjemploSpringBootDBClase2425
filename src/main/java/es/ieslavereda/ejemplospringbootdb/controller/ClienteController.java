package es.ieslavereda.ejemplospringbootdb.controller;

import es.ieslavereda.ejemplospringbootdb.repository.Cliente;
import es.ieslavereda.ejemplospringbootdb.service.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}
