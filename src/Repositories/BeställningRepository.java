package Repositories;

import Komponenter.Beställning;
import Komponenter.Kundvagn;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class BeställningRepository {
    private final Properties p = new Properties();

    public BeställningRepository() {
        try {
            p.load(new FileInputStream("src/Settnings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Beställning> beställningSkapare(List<Kundvagn> kundvagnLista){

        List<Beställning> beställningLista = new ArrayList<>();
        ResultSet rs = null;
        String query = "select * from beställning";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();){

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                int tempID = rs.getInt("kundvagnID");
                beställningLista.add(new Beställning(rs.getInt("id"),
                        rs.getString("datum"),
                        kundvagnLista.stream().filter(k -> k.getId() == tempID).toList().get(0)));
            }
            return beställningLista;
        } catch (SQLException e) {
            System.out.println("ajajaj");
            throw new RuntimeException(e);
        }
    }
}
