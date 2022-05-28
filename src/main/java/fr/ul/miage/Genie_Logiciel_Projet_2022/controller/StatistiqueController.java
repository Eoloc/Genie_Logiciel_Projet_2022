package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class StatistiqueController {

    public StatistiqueController(){}

    // cette fonction permet d'afficher des statistique globales

    public void getStatistiques(DatabaseController bdd) throws SQLException {

        int qteBorne = 0;
        int qteBorneDispo = 0;
        int nbreClient = 0;
        int nbreReservation = 0;
        int nbreVehicules = 0;
        Statement st = bdd.getCon().createStatement();
        ResultSet rs = st.executeQuery("SELECT count(etatborne) AS totalBorneDispo FROM borne where etatborne='Disponible'");
        while(rs.next()) {
            qteBorneDispo = rs.getInt("totalBorneDispo");
        }
        ResultSet rs1 = st.executeQuery("SELECT count(etatborne) AS total FROM borne ");
        while(rs1.next()) {
            qteBorne = rs1.getInt("total");
        }
        ResultSet rs2 = st.executeQuery("SELECT count(idcompte) AS nombreClient FROM compte ");
        while(rs2.next()) {
            nbreClient = rs2.getInt("nombreClient");
        }
        ResultSet rs3 = st.executeQuery("SELECT count(idreservation) AS nombreReservation FROM reservation ");
        while(rs3.next()) {
            nbreReservation = rs3.getInt("nombreReservation");
        }
        ResultSet rs4 = st.executeQuery("SELECT count(immatriculation) AS nombreVehicule FROM vehicule ");
        while(rs4.next()) {
            nbreVehicules = rs4.getInt("nombreVehicule");
        }

        System.out.println("\n =============Statistiques générales========= \n");
        System.out.println("\n le nombre des bornes disponible est de : "+qteBorneDispo);
        System.out.println("\n le nombre de bornes totale est de : "+qteBorne);
        System.out.println("\n le nombre des clients inscrits est : "+nbreClient);
        System.out.println("\n le nombre de réservation est de : "+nbreReservation);
        System.out.println("\n le nombre de véhicule est de : "+nbreVehicules);

    }
}

