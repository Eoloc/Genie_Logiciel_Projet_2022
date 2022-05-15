package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class DatabaseController {
    private Connection con;
    private String user;
    private String password;

    public DatabaseController(String u, String pw) {
        user = u;
        password = pw;
        con = null;
    }

    public void connexionDatabase() {
        String url = "jdbc:postgresql://localhost:5432/localhost";

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public ArrayList<Compte> getAllClient() throws SQLException {
        ArrayList<Compte> comptes = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM compte");
        while(rs.next()) {
            Compte compte = new Compte(rs.getInt(1),rs.getString(2),
                    rs.getString(3),rs.getString(4),rs.getString(5),
                    rs.getInt(6),rs.getBoolean(7),rs.getBoolean(8),
                    rs.getBoolean(9));
            comptes.add(compte);
        }
        return comptes;
    }

    public Connection getCon() {
        return con;
    }
}
