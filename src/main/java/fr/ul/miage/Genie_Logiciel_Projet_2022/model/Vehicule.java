package fr.ul.miage.Genie_Logiciel_Projet_2022.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;

public class Vehicule {
	private String immatriculation;
	private String marque;
	private String modele;
	private String couleur;


	public Vehicule() {
		
	}

	public Vehicule(String immatriculation, String marque, String modele, String couleur) {
		this.immatriculation = immatriculation;
		this.marque = marque;
		this.modele = modele;
		this.couleur = couleur;
	}
	
	@Override
    public String toString() {
        return "Vehicule{" +
                "immatriculation=" + this.immatriculation +
                ", marque='" + this.marque + '\'' +
                ", modele='" + this.modele + '\'' +
                ", couleur='" + this.couleur + '\'' +
                '}';
    }
	
	 public static ArrayList<Vehicule> getAllVehicule(DatabaseController bdd) throws SQLException {
	        ArrayList<Vehicule> vehicules = new ArrayList<>();
	        Statement st = bdd.getCon().createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM vehicule");
	        while(rs.next()) {
	        	Vehicule vehicule = new Vehicule(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
	        	vehicules.add(vehicule);
	        }
	        return vehicules;
	  }
	 
	 public static Vehicule getVehiculeByImmatriculation(DatabaseController bdd, String immatriculation) throws SQLException {
		 Vehicule vehicule = null;
		 Statement st = bdd.getCon().createStatement();
		 ResultSet rs = st.executeQuery("SELECT * FROM vehicule where immatriculation='" + immatriculation + "'");
		 while(rs.next()) {
			 vehicule = new Vehicule(rs.getString(1), rs.getString(2),rs.getString(3),rs.getString(4));
		 }
		 return vehicule;
	 }

}
