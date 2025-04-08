package es.ieslavereda.ejemplospringbootdb.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
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

        try(Connection con = dataSource.getConnection();){

            Statement stmt = con.createStatement();
            String sql = "select * from Cliente where id="+id;
            ResultSet rs = stmt.executeQuery(sql);

            if (rs.next())
                return Cliente.builder()
                        .id(rs.getLong("id"))
                        .nombre(rs.getString("nombre"))
                        .apellidos(rs.getString("apellidos"))
                        .fechaNacimiento(rs.getDate("fecha_nacimiento"))
                        .imagen(rs.getString("imagen"))
                        .build();




        }catch (Exception e){
            e.printStackTrace();
        }

        return null;

    }

    public Cliente add(Cliente cliente) {


        String sql = "insert into " +
                "Cliente (nombre,apellidos,dni,fecha_nacimiento,imagen) " +
                "values ('"+cliente.getNombre()+"','"+cliente.getApellidos()+"','"+cliente.getDni()+"','"+cliente.getFechaNacimiento()+"','"+cliente.getImagen()+"')";

        try(Connection con = dataSource.getConnection()){

            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            //ResultSet rs = stmt.getGeneratedKeys();
            return cliente;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Cliente remove(Long id) {

    }
}
