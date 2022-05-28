package fr.ul.miage.Genie_Logiciel_Projet_2022.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;

public class HistoriqueDepense {
	private int idCompte;
	private int idReservation;
	private double montant;

	public HistoriqueDepense() {
		
	}

	public HistoriqueDepense(int idCompte, int idReservation, double montant) {
		super();
		this.idCompte = idCompte;
		this.idReservation = idReservation;
		this.montant = montant;
	}
	
	@Override
    public String toString() {
        return "HistoriqueDepense{" +
                "idCompte=" + this.idCompte +
                ", idReservation='" + this.idReservation + '\'' +
                ", montant='" + this.montant + '\'' +
                '}';
    }
	
	public static ArrayList<HistoriqueDepense> getAllHistoriqueDepense(DatabaseController bdd) throws SQLException {
        ArrayList<HistoriqueDepense> historiqueDepenses = new ArrayList<>();
        Statement st = bdd.getCon().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM HistoriqueDepense");
        while(rs.next()) {
        	HistoriqueDepense historiqueDepense = new HistoriqueDepense(rs.getInt(1), rs.getInt(2),rs.getDouble(3));
        	historiqueDepenses.add(historiqueDepense);
        }
        return historiqueDepenses;
	}
	
	public static ArrayList<HistoriqueDepense> getHistoriqueByUser(DatabaseController bdd, int idUser) throws SQLException {
        ArrayList<HistoriqueDepense> historiqueDepenses = new ArrayList<>();
        Statement st = bdd.getCon().createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM HistoriqueDepense WHERE idcompte='"+idUser+"'");
        while(rs.next()) {
        	HistoriqueDepense historiqueDepense = new HistoriqueDepense(rs.getInt(1), rs.getInt(2),rs.getDouble(3));
        	historiqueDepenses.add(historiqueDepense);
        }
        return historiqueDepenses;
	}

	public int getIdCompte() {
		return idCompte;
	}

	public void setIdCompte(int idCompte) {
		this.idCompte = idCompte;
	}

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
	
	
	
}
