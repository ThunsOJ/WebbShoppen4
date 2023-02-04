package Repositories;

import Komponenter.Kund;
import Komponenter.Kundvagn;
import Komponenter.Sko;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class KundvagnRepository {

    private final Properties p = new Properties();

    public KundvagnRepository() {
        try {
            p.load(new FileInputStream("src/Settnings.properties"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Kundvagn> kundvagnSkapare(List<Sko> skoLista, List<Kund> kundLista){

        List<Kundvagn> kundvagnLista = new ArrayList<>();
        ResultSet rs = null;
        String query = "select * from kundvagn ";
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();){

            rs = stmt.executeQuery(query);
            while (rs.next()) {
                int tempID = rs.getInt("kundID");

                kundvagnLista.add(new Kundvagn(rs.getInt("id"),
                        skoÄgare(skoLista, rs.getInt("id")),
                        kundLista.stream().filter(k -> k.getId() == tempID).toList().get(0)));
            }
            return kundvagnLista;
        } catch (SQLException e) {
            System.out.println("ajajaj");
            throw new RuntimeException(e);
        }
    }

    public List<Sko> skoÄgare(List<Sko> skoLista, int myID){
        ResultSet rs = null;
        String query = "select * from innehåller "+
                "inner join sko on innehåller.SkoID = sko.id where SkoID = sko.id and KundvagnID = " + myID;
        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("password"));
             Statement stmt = con.createStatement();){

            rs = stmt.executeQuery(query);

            List<Sko> nyLista = new ArrayList<>();
            while (rs.next()){
                int id = rs.getInt("skoID");
                nyLista.add(skoLista.stream().filter(s -> s.getId() == id).toList().get(0));
            }
            return nyLista;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    public String addToCart(int kundID, int kundvagnID, int skoID) {
        String query = "call AddToCart(?, ?, ?)";

        try (Connection con = DriverManager.getConnection(
                p.getProperty("url"),
                p.getProperty("name"),
                p.getProperty("password"));
             CallableStatement stmt = con.prepareCall(query)) {

            stmt.setInt(1, kundID);
            stmt.setInt(2, kundvagnID);
            stmt.setInt(3, skoID);
            stmt.executeQuery();


        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return "Kunde inte lägga till";
        }
        return "varan är nu tillagd";
    }
}
