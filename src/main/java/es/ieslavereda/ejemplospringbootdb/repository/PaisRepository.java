package es.ieslavereda.ejemplospringbootdb.repository;

import es.ieslavereda.ejemplospringbootdb.repository.model.Pais;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class PaisRepository {

    @Autowired
    @Qualifier("mysqlDataSource")
    private DataSource dataSource;

    public Pais crear(Pais pais) throws SQLException {

        String sql = "INSERT INTO Pais VALUES (?,?,?)";

        try(Connection con = dataSource.getConnection()){

            PreparedStatement pstmt = con.prepareCall(sql);

            pstmt.setString(1,pais.getNombre());
            pstmt.setString(2,pais.getUrl());
            pstmt.setString(3,pais.getIdioma());

            pstmt.executeUpdate();

            return pais;

        }


    }

    public Pais get(String nombre) throws Exception {

        String sql = "SELECT * FROM Pais WHERE nombre=?";
        try(Connection con = dataSource.getConnection()){

            PreparedStatement pstmt = con.prepareCall(sql);

            pstmt.setString(1,nombre);

            ResultSet rs = pstmt.executeQuery();


            if(rs.next()){
                return Pais.builder()
                        .nombre(rs.getString(1))  // Obtenemos por posicion (empieza en 1)
                        .url(rs.getString("url")) // Obtenemos por nombre del campo
                        .idioma(rs.getString(3))
                        .build();
            }

            throw new Exception("No se ha encontrado el pais " + nombre);


        }

    }

    public List<Pais> getAll() throws SQLException {

        String sql = "SELECT * FROM Pais";

        try(Connection con = dataSource.getConnection()){

            PreparedStatement pstmt = con.prepareCall(sql);



            ResultSet rs = pstmt.executeQuery();

            List<Pais> paises = new ArrayList<>();

            while(rs.next()){
                paises.add(Pais.builder()
                        .nombre(rs.getString(1))  // Obtenemos por posicion (empieza en 1)
                        .url(rs.getString("url")) // Obtenemos por nombre del campo
                        .idioma(rs.getString(3))
                        .build());
            }

            return paises;

        }

    }
}
