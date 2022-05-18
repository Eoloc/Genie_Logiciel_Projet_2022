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
	public String seConnecter(String email,String mdp ) throws SQLException {
		StringBuilder res = new StringBuilder();
		 Compte compte = new Compte();
		//ArrayList<Compte> comptes = new ArrayList<>();
		compte.getClientByEmailPassword(bdd,email,mdp);

			if (email == compte.getEmail() && mdp == compte.getMdp()) {
				System.out.println("Connexion est réussi");
			}
			else if (email != compte.getEmail() || mdp != compte.getMdp()) {
				System.out.println("Les informations que vous avez fourni ne sont pas correctes");
			}
			else {
				System.out.println("Les informations que vous avez fourni ne sont pas correctes");
			}

		return res.toString();
	}

	public String inscrire(Compte cpt) throws SQLException {
		Compte compte = new Compte();
		compte.inscrire(bdd, cpt);
		return "Le compte est bien créé";
	}

}
