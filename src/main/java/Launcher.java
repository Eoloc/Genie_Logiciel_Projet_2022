import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.BorneController;
import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;
import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.ReservationController;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Reservation;
import fr.ul.miage.Genie_Logiciel_Projet_2022.view.MenuPrincipal;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Launcher {
    public static void main(String[] args) throws SQLException {
		Ini ini = null;
		try {
			ini = new Ini(new File("src/main/java/bdd.ini"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		assert ini != null;
		DatabaseController bdd = new DatabaseController(ini.get("database", "user"), ini.get("database", "password"), ini.get("database", "port"));
        Compte cpt = new Compte();
		bdd.connexionDatabase();

        ArrayList<Compte> comptes = bdd.getAllClient();
        BorneController borneController = new BorneController(bdd);
        ReservationController reservationController = new ReservationController(bdd);
        for(Compte compte : comptes){
            System.out.println(compte);
        }

        ArrayList<ArrayList<String>> listeMenus = new ArrayList<ArrayList<String>>();
        
        ArrayList<String> listeMenusAccueil = new ArrayList<String>();
        listeMenusAccueil.add("Inscription");
        listeMenusAccueil.add("Connexion");
        listeMenusAccueil.add("Quitter");
        
        ArrayList<String> listeMenusPrincipale = new ArrayList<String>();
        listeMenusPrincipale.add("Gestion réservation");
        listeMenusPrincipale.add("Gestion immatriculations");
        listeMenusPrincipale.add("Gestion profil");
        listeMenusPrincipale.add("Quitter");
        
        listeMenus.add(listeMenusAccueil);
        listeMenus.add(listeMenusPrincipale);
        
        ArrayList<ArrayList<String>> listeSousMenus = new ArrayList<ArrayList<String>>();
        
        ArrayList<String> listeSousMenus1 = new ArrayList<String>();
        listeSousMenus1.add("Passer réservation");
        listeSousMenus1.add("Visualiser réservations");
        listeSousMenus1.add("Modifier réservations");
        listeSousMenus1.add("Voir disponibilités bornes");
		listeSousMenus1.add("Quitter");
               
        ArrayList<String> listeSousMenus2 = new ArrayList<String>();
        listeSousMenus2.add("Visualiser immatriculations");
        listeSousMenus2.add("Ajouter immatriculation");
        listeSousMenus2.add("Supprimer immatriculation");
        listeSousMenus2.add("Quitter");

        ArrayList<String> listeSousMenus3 = new ArrayList<String>();
        listeSousMenus3.add("Modifier informations");
        listeSousMenus3.add("Visualiser informations");
        listeSousMenus3.add("Visualiser dépenses");
        listeSousMenus3.add("Quitter");
        
        listeSousMenus.add(listeSousMenus1);
        listeSousMenus.add(listeSousMenus2);
        listeSousMenus.add(listeSousMenus3);
        
        Compte c = null;
        MenuPrincipal m = new MenuPrincipal(c,listeMenus,listeSousMenus);
        int choixUserMenuPrincipal = -1;
        int choixUserMenuSecondaire;
        boolean finProgramme = false;    
        while(!finProgramme) {
        	if(c == null) {
				choixUserMenuPrincipal = m.afficherMenu();
            	switch(choixUserMenuPrincipal) {
    	        	case 0:
						String [] informationInscription = m.afficherMenuInscription();
						//c=cpt.inscrire(bdd,c);
    	        		//appel méthode inscription (voir retour menuInscription)
    	        		break;
    	        	case 1:
						String [] information = m.afficherMenuConnexion();
						c = cpt.getClientByEmailPassword(bdd,information[0], information[1]);
						if(c != null){
							m.setUser(c);
						}
    	        		break;
    	        	case 2:
    	        		finProgramme = true;
    	        		break;
            	}

            }else {
				choixUserMenuPrincipal = m.afficherMenu();
				choixUserMenuSecondaire = -1;
            	choixUserMenuSecondaire = m.afficherSousMenu(choixUserMenuPrincipal);
            	switch(choixUserMenuPrincipal) {
    	        	case 0:
    	        		switch(choixUserMenuSecondaire) {
    			        	case 0:
    			        		System.out.println("Menu réservation");
    			        		//TODO
    			        		break;
    			        	case 1:
    			        		String listeReserv = reservationController.consulterReservationParUtilisateur(c.getIdCompte());
    			        		m.afficherListeReservations(listeReserv);
    			        		//TODO
    			        		break;
    			        	case 2:
    			        		System.out.println("Modification réservation");
    			        		//TODO
    			        		break;
    			        	case 3:
    			        		System.out.println("Disponibilité bornes");
    			        		//TODO
    			        		break;
							case 4:
								break;
    	        		}
    	        		break;
    	        	case 1:
    	        		switch(choixUserMenuSecondaire) {
			        	case 0:
			        		System.out.println("Visualiser immatriculations");
			        		//TODO
			        		break;
			        	case 1:
			        		System.out.println("Ajouter immatriculation");
			        		//TODO
			        		break;
			        	case 2:
			        		System.out.println("Supprimer immatriculation");
			        		//TODO
			        		break;
						case 3:
							break;
		        	}
    	        		break;
    	        	case 2:
    	        		switch(choixUserMenuSecondaire) {
			        	case 0:
			        		Compte cModifie = m.afficherModificationInformationsPersonnelles();
			        		c.setEmail(cModifie.getEmail());
			        		c.setMdp(cModifie.getMdp());
			        		c.setNom(cModifie.getNom());
			        		c.setPrenom(cModifie.getPrenom());
			        		c.setAge(cModifie.getAge());
				        	c.updateCompte(bdd);
			        		//TODO
			        		break;
			        	case 1:
			        		m.afficherInformationsPersonnelles();
			        		break;
			        	case 2:
			        		System.out.println("Visualisation dépenses");
			        		//TODO
			        		break;
						case 3:
							break;
	        		}
    	        		break;
    	        	case 3:
    	        		c = null;
						m.setUser(c);
    	        		break;
            	}
            }
        }
        

    }

}
