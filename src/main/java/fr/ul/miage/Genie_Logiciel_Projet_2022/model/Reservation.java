package fr.ul.miage.Genie_Logiciel_Projet_2022.model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;

public class Reservation {
	private int idReservation;
	private String dateDebut;
	private String dateFin;
	private String etatReservation;
	private int idCompte;
	private int idBorne;
	private boolean estPermanente;

	public Reservation() {
		
	}
	
	public Reservation(int idReservation, String dateDebut, String dateFin,
			String etatReservation, int idCompte, int idBorne, boolean estPermanente) {
		this.idReservation = idReservation;
		this.dateDebut = dateDebut;
		this.dateFin = dateFin;
		this.etatReservation = etatReservation;
		this.idCompte = idCompte;
		this.idBorne = idBorne;
		this.estPermanente = estPermanente;
	}

	@Override
    public String toString() {
        return "Réservation{" +
                "idReservation=" + this.idReservation +
                ", dateDebut='" + this.dateDebut + '\'' +
                ", dateFin='" + this.dateFin + '\'' +
                ", etatReservation='" + this.etatReservation + '\'' +
                ", idCompte='" + this.idCompte + '\'' +
                ", idBorne=" + this.idBorne +
                ", estPermanente=" + this.estPermanente +
                '}';
    }
	
	 public static ArrayList<Reservation> getAllReservation(DatabaseController bdd) throws SQLException {
	        ArrayList<Reservation> reservations = new ArrayList<>();
	        Statement st = bdd.getCon().createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM reservation");
	        while(rs.next()) {
	        	Reservation reservation = new Reservation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getBoolean(7));
	        	reservations.add(reservation);
	        }
	        return reservations;
	  }
	 
	 
	 
	 
	 
	
}
