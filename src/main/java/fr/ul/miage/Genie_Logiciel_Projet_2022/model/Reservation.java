package fr.ul.miage.Genie_Logiciel_Projet_2022.model;

import java.sql.*;
import java.util.ArrayList;

import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;

import javax.xml.crypto.Data;

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
	
	public Reservation(int idReservation, String dateDebut, String dateFin, String etatReservation, int idCompte, int idBorne, boolean estPermanente) {
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
	 
	 public static ArrayList<Reservation> getReservationbyUser(DatabaseController bdd, int idCompte) throws SQLException {
	        ArrayList<Reservation> reservations = new ArrayList<>();
	        Statement st = bdd.getCon().createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM reservation WHERE idCompte = '"+idCompte+"'");
	        while(rs.next()) {
	        	Reservation reservation = new Reservation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getBoolean(7));
	        	reservations.add(reservation);
	        }
	        return reservations;
	}

	public static Reservation getReservationbyDateAndUser(DatabaseController bdd, String dateDebut, String dateFin, int idCompte) throws SQLException {
		Reservation reservation = null;
		Statement st = bdd.getCon().createStatement();
		System.out.println(dateDebut);
		System.out.println(dateFin);
		ResultSet rs = st.executeQuery("SELECT * FROM reservation WHERE dateDebut = '"+dateDebut+"' AND dateFin = '"+dateFin+"' AND idCompte = '"+idCompte+"'");
		while(rs.next()) {
			reservation = new Reservation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getBoolean(7));
		}
		return reservation;
	}

	public static Reservation getReservationbyId(DatabaseController bdd, int idReservation) throws SQLException {
		Reservation reservation = null;
		Statement st = bdd.getCon().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM reservation WHERE idReservation = '"+idReservation+"'");
		while(rs.next()) {
			reservation = new Reservation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getBoolean(7));
		}
		return reservation;
	}

	public static ArrayList<Reservation> getAllReservationBetweenDates(DatabaseController bdd, String dateDebut, String dateFin) throws SQLException {
		ArrayList<Reservation> reservations = new ArrayList<>();
		Statement st = bdd.getCon().createStatement(); // Si on commence avant une réservation et qu'on finit pendant
		ResultSet rs = st.executeQuery("select * from reservation where dateDebut >= '" + dateDebut + "' and dateDebut <= '" + dateFin + "' and dateFin >= '" + dateFin + "'");
		while(rs.next()) {
			Reservation reservation = new Reservation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getBoolean(7));
			reservations.add(reservation);
		}
		st = bdd.getCon().createStatement(); // Si on est pendant une réservation
		rs = st.executeQuery("select * from reservation where dateDebut <= '" + dateDebut + "' and dateFin >= '" + dateFin + "'");
		while(rs.next()) {

			Reservation reservation = new Reservation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getBoolean(7));
			if (!reservations.contains(reservation)) {reservations.add(reservation);}

		}
		st = bdd.getCon().createStatement(); // Si on commence pendant une réservation et qu'on finit après
		rs = st.executeQuery("select * from reservation where dateDebut <= '" + dateDebut + "' and dateFin >= '" + dateDebut + "' and dateFin <= '" + dateFin + "'");
		while(rs.next()) {
			Reservation reservation = new Reservation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getBoolean(7));
			if (!reservations.contains(reservation)) {reservations.add(reservation);}
		}
		st = bdd.getCon().createStatement(); // Si on englobe une réservation
		rs = st.executeQuery("select * from reservation where dateDebut >= '" + dateDebut + "' and dateFin <= '" + dateFin + "'");
		while(rs.next()) {
			Reservation reservation = new Reservation(rs.getInt(1), rs.getString(2),rs.getString(3),rs.getString(4),rs.getInt(5),rs.getInt(6),rs.getBoolean(7));
			if (!reservations.contains(reservation)) {reservations.add(reservation);}
		}
		return reservations;
	}

	public static Reservation insertNewReservation(DatabaseController bdd, String dateDebut, String dateFin, String etatReservation, int idCompte, int idBorne, boolean estPermanente) throws SQLException {
		String sql = "INSERT INTO reservation (datedebut, datefin, etatreservation, idcompte, idborne, estPermanente)"
				+ "VALUES(?, ?, ?, ?, ?, ?)";
		try {
			PreparedStatement pstmt = bdd.getCon().prepareStatement(sql);
			pstmt.setTimestamp(1, Timestamp.valueOf(dateDebut));
			pstmt.setTimestamp(2, Timestamp.valueOf(dateFin));
			pstmt.setString(3, etatReservation);
			pstmt.setInt(4, idCompte);
			pstmt.setInt(5, idBorne);
			pstmt.setBoolean(6, estPermanente);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			return null;
		}
		return getReservationbyDateAndUser(bdd, dateDebut, dateFin, idCompte);
	}

	public Boolean deleteReservation(DatabaseController bdd, Reservation reservation) throws SQLException {
		String sql = "DELETE FROM reservation WHERE idReservation='" + reservation.getIdReservation() +"'";
		try {
			PreparedStatement pstmt = bdd.getCon().prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			return false;
		}
		return true;
	}

	public static Boolean updateReservation(DatabaseController bdd, int idReservation, String dateDebut, String dateFin, boolean estPermanente, int idBorne) {
		String sql = "UPDATE reservation SET dateDebut='" + dateDebut + "', dateFin='" + dateFin + "', estPermanente='" + estPermanente + "', idBorne='" + idBorne +"' WHERE idReservation='" + idReservation +"'";
		try {
			PreparedStatement pstmt = bdd.getCon().prepareStatement(sql);
			pstmt.executeUpdate();
		} catch (SQLException ex) {
			return false;
		}
		return true;
	}
	
	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public String getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(String dateDebut) {
		this.dateDebut = dateDebut;
	}


	public String getDateFin() {
		return dateFin;
	}


	public void setDateFin(String dateFin) {
		this.dateFin = dateFin;
	}

	public String getEtatReservation() {
		return etatReservation;
	}


	public void setEtatReservation(String etatReservation) {
		this.etatReservation = etatReservation;
	}


	public int getIdCompte() {
		return idCompte;
	}


	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}


	public int getIdBorne() {
		return idBorne;
	}

	public boolean getEstPermanente() {
		return estPermanente;
	}

	public void setIdBorne(int idBorne) {
		this.idBorne = idBorne;
	}

	public boolean isEstPermanente() {
		return estPermanente;
	}

	public void setEstPermanente(boolean estPermanente) {
		this.estPermanente = estPermanente;
	}

}
