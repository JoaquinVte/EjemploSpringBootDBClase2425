package es.ieslavereda.ejemplospringbootdb.repository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

import java.sql.*;
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

    public Cliente remove(Long id) throws SQLException {

        Cliente cliente = getCliente(id);

        if(cliente==null) throw new SQLException("El cliente no existe");


        try(Connection con = dataSource.getConnection()){

            String sql = "DELETE FROM Cliente WHERE id=?";

            PreparedStatement pstmt = con.prepareStatement(sql);
            int pos =0;
            pstmt.setLong(++pos,id);

            if(pstmt.executeUpdate()==1)
                return cliente;
            else
                throw new SQLException("No se ha podido eliminar");

        } catch (Exception e) {
            throw e;
        }

    }
}
