package es.ieslavereda.ejemplospringbootdb.service;

import es.ieslavereda.ejemplospringbootdb.repository.model.Cliente;
import es.ieslavereda.ejemplospringbootdb.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> getClientes(){
        return clienteRepository.getClientes();
    }

    public Cliente getCliente(Long id) {
        return clienteRepository.getCliente(id);
    }

    public Cliente add(Cliente cliente) {
        return clienteRepository.add(cliente);
    }

    public Cliente remove(Long id) throws SQLException {
        return clienteRepository.remove(id);
    }

    public Cliente update(Cliente cliente) {
        return clienteRepository.update(cliente);
    }
}
