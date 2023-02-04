package Repositories;

import Komponenter.Kund;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KundRepository {

    private final Properties p = new Properties();

    public KundRepository() {
        try {
            p.load(new FileInputStream("src/Settnings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Kund> kundSkapare(){

        List<Kund> kundLista = new ArrayList<>();
        ResultSet rs = null;
        String query = "select * from kund";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();){

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                kundLista.add(new Kund(rs.getInt("id"),
                        rs.getString("namn"),
                        rs.getString("Adress"),
                        rs.getString("Ort"),
                        rs.getString("lösen")));
            }
            return kundLista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
