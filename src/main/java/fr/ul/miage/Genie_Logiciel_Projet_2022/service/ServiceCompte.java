package fr.ul.miage.Genie_Logiciel_Projet_2022.service;

import java.sql.SQLException;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;
import org.springframework.security.core.session.SessionRegistry;


public class ServiceCompte {

	SessionRegistry sessionRegistry;

	Compte compte = new Compte();
	
	//M�thode connexion
	public void connexion (String email, String mdp)
	{
		if (email == compte.getEmail() && mdp == compte.getMdp()) {
			System.out.println("Connexion est r�ussi");
		}
		else if (email != compte.getEmail() || mdp != compte.getMdp()) {
			System.out.println("Les informations que vous avez fourni ne sont pas correctes");
		}
		else {
			System.out.println("Les informations que vous avez fourni ne sont pas correctes");
		}
	}
	
	//Méthode s'inscrire
	//à développer avec version bdd
	public void inscrire(Compte cpt) {
		compte.setEmail(cpt.getEmail());
		compte.setMdp(cpt.getMdp());
		compte.setNom(cpt.getNom());
		compte.setPrenom(cpt.getPrenom());
		compte.setAge(cpt.getAge());
		compte.setEstClient(cpt.isEstClient());
		compte.setEstGerant(cpt.isEstGerant());
		compte.setEstAdministrateur(cpt.isEstAdministrateur());
		compte.setImmatriculation(cpt.getImmatriculation());
		
	}
	
	//Méthode déconnexion
    //à développer avec version bdd
	 
	public void deconnexion(String idCompte) throws SQLException {
		// requete sql à mettre pour récupérer idCompte where email = email connecté
		// String sql = "SELECT idCompte from compte c where c.email = compte.getEmail()";
		// PreparedStatement prep = conn.prepareStatement(sql);
		sessionRegistry.getSessionInformation(idCompte).expireNow();

		
	}
	

}
