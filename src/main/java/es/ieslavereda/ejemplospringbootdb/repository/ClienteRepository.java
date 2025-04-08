package es.ieslavereda.ejemplospringbootdb.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ClienteRepository {


    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource dataSource;

    public List<Cliente> getClientes(){

        List<Cliente> clientes = new ArrayList<>();

        try(Connection con = dataSource.getConnection();){

            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente");

            while (rs.next()){

                clientes.add(Cliente.builder()
                        .id(rs.getLong("id"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .fechaNacimiento(rs.getDate("fecha_nacimiento"))
                        .imagen(rs.getString("imagen"))
                        .build());

            }

        }catch (Exception e){
            e.printStackTrace();
        }




        return clientes;
    }

    public Cliente getCliente(Long id){
        
    }

}
