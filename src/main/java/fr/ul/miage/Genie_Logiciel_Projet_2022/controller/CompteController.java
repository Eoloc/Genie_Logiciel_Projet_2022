package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CompteController {

	private DatabaseController bdd;

	public CompteController(DatabaseController bd) {
		bdd = bd;
	}
	public Compte seConnecter(String email,String mdp ) throws SQLException {
		Compte cpt ;
		Compte compte = new Compte();

		cpt = compte.getClientByEmailPassword(bdd,email,mdp);

			if (cpt.equals(null) ){
				System.out.println("Les informations que vous avez fourni ne sont pas correctes");
			}
			else  {
				System.out.println("Connexion réussi");
			}
		return cpt;
	}

	public Compte inscrire(int id, String email, String mdp, String nom, String prenom, int age, boolean estClient, boolean estGerant, boolean estAdministrateur) throws SQLException {

		Compte cpt ;
		Compte compte = new Compte();
		cpt = compte.inscrire(bdd,id,email,mdp,nom,prenom,age,estClient,estGerant,estAdministrateur);
			System.out.println("Le compte est bien crée");
		return cpt;
	}




}
