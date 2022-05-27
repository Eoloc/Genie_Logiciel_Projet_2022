package fr.ul.miage.Genie_Logiciel_Projet_2022.model;

import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Borne {
    private int idBorne;
    private String etatBorne;

    public Borne(int id, String etat) {
        idBorne = id;
        etatBorne = etat;
    }

    public static ArrayList<Borne> getAllBorne(DatabaseController bdd) throws SQLException {
        ArrayList<Borne> bornes = new ArrayList<>();
        Statement st = bdd.getCon().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM borne");
        while(rs.next()) {
            Borne borne = new Borne(rs.getInt(1), rs.getString(2));
            bornes.add(borne);
        }
        return bornes;
    }

    public static ArrayList<Borne> getAllBorneByEtat(DatabaseController bdd, String etatBorne) throws SQLException {
        ArrayList<Borne> bornes = new ArrayList<>();
        Statement st = bdd.getCon().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM borne WHERE etatborne='" + etatBorne + "'");
        while(rs.next()) {
            Borne borne = new Borne(rs.getInt(1), rs.getString(2));
            bornes.add(borne);
        }
        return bornes;
    }

    public int getIdBorne() {
        return idBorne;
    }

    public String getEtatBorne() {
        return etatBorne;
    }
}
