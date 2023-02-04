package Repositories;

import Komponenter.Kategori;
import Komponenter.Sko;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SkoRepository {
    private final Properties p = new Properties();

    public SkoRepository() {
        try {
            p.load(new FileInputStream("src/Settnings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Sko> skoSkapare(){

        List<Sko> skoLista = new ArrayList<>();
        ResultSet rs = null;
        String query = "select * from sko";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();){

            rs = stmt.executeQuery(query);

            while (rs.next()) {
                skoLista.add(new Sko(rs.getInt("id"),
                        rs.getString("märke"),
                        rs.getInt("storlek"),
                        rs.getString("färg"),
                        rs.getString("modell"),
                        rs.getString("kön"),
                        rs.getInt("pris"),
                        rs.getInt("saldo"),
                        kategoriSkapare(rs.getInt("id"))));
            }
            return skoLista;
        } catch (SQLException e) {
            System.out.println("ajajaj");
            throw new RuntimeException(e);
        }
    }

    public List<Kategori> kategoriSkapare(int id){
        ResultSet rs = null;
        String query = "select * from sko "+
                "inner join kategoriseras on sko.id = kategoriseras.skoID "+
                "inner join kategori on kategoriseras.kategoriID = kategori.id "+
                "where sko.id = ?";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("password"));
             PreparedStatement stmt = con.prepareStatement(query)){

            stmt.setInt(1, id);

            rs = stmt.executeQuery();
            List<Kategori> kategoriLista = new ArrayList<>();

            while (rs.next()) {
                kategoriLista.add(new Kategori(rs.getInt("id"), rs.getString("kategori")));
            }
            return kategoriLista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
