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

	public void getDepenseByClient(DatabaseController bdd, int idCompte) throws SQLException {
		ArrayList<HistoriqueDepense> historiqueDepenses = new ArrayList<>();
		double montant=0;
		Statement st = bdd.getCon().createStatement();
		ResultSet rs = st.executeQuery("SELECT * FROM HistoriqueDepense where idCompte="+idCompte);
		while(rs.next()) {
			HistoriqueDepense historiqueDepense = new HistoriqueDepense(rs.getInt(1), rs.getInt(2),rs.getDouble(3));
			historiqueDepenses.add(historiqueDepense);
		}
		for(int i=0;i<historiqueDepenses.size();i++ ){
			montant=montant + historiqueDepenses.get(i).montant;
		}
		System.out.println("l'id client : "+idCompte+ " a dépensé une totalité de :"+montant);
	}

}
