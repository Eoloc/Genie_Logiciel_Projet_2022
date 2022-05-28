package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.HistoriqueDepense;

public class HistoriqueDepenseController {
	private DatabaseController bdd;

	public HistoriqueDepenseController(DatabaseController bd) {
		bdd = bd;
	}
	
	public String consulterDepenses(int idUser) throws SQLException {
		StringBuilder res = new StringBuilder();
        ArrayList<HistoriqueDepense> historiqueDepenses = HistoriqueDepense.getHistoriqueByUser(bdd,idUser);
        Double somme = 0.0;
        for(HistoriqueDepense depense : historiqueDepenses){
        	res.append("Réservation "+depense.getIdReservation()).append(" : "+depense.getMontant()+"€\n");
        	somme += depense.getMontant();
        }
        res.append("Montant total dépenses : ").append(somme+"\n");
        return res.toString();
	}
}
