package es.ieslavereda.ejemplospringbootdb.service;

import es.ieslavereda.ejemplospringbootdb.repository.Cliente;
import es.ieslavereda.ejemplospringbootdb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes(){
        return clienteRepository.getClientes();
    }

}
