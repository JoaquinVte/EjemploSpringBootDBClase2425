package es.ieslavereda.ejemplospringbootdb.service;

import es.ieslavereda.ejemplospringbootdb.repository.PaisRepository;
import es.ieslavereda.ejemplospringbootdb.repository.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public Pais crear(Pais pais) throws SQLException {
        return paisRepository.crear(pais);
    }

    public Pais obtener(String nombre) throws Exception {
        return paisRepository.get(nombre);
    }

    public List<Pais> getAll() throws SQLException {
        return paisRepository.getAll();
    }
}
