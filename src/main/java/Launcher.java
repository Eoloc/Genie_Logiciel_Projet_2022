import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;
import fr.ul.miage.Genie_Logiciel_Projet_2022.view.MenuPrincipal;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;


public class Launcher {
    public static void main(String[] args) throws SQLException {
        DatabaseController bdd = new DatabaseController("postgres", "fabien");
        bdd.connexionDatabase();
        ArrayList<Compte> comptes = bdd.getAllClient();
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
        
        ArrayList<String> listeSousMenus2 = new ArrayList<String>();
        listeSousMenus2.add("Modifier informations");
        listeSousMenus2.add("Visualiser informations");
        listeSousMenus2.add("Visualiser dépenses");
       
        ArrayList<String> listeSousMenus3 = new ArrayList<String>();
        listeSousMenus3.add("Visualiser immatriculations");
        listeSousMenus3.add("Ajouter immatriculation");
        listeSousMenus3.add("Supprimer immatriculation");
        
        listeSousMenus.add(listeSousMenus1);
        listeSousMenus.add(listeSousMenus2);
        listeSousMenus.add(listeSousMenus3);
        
        
        
        
        Compte c = null;
        MenuPrincipal m = new MenuPrincipal(c,listeMenus,listeSousMenus);
        int choixUserMenuPrincipal = m.afficherMenu();
        int choixUserMenuSecondaire;
        boolean finProgramme = false;    
        while(finProgramme == false) {
        	if(c == null) {
            	switch(choixUserMenuPrincipal) {
    	        	case 0:
    	        		m.afficherMenuInscription();
    	        		//appel méthode inscription (voir retour menuInscription)
    	        	break;
    	        	case 1:
    	        		m.afficherMenuConnexion();
    	        		//appel méthode connexion (voir retour menuConnexion)
    	        	break;
    	        	case 2:
    	        		finProgramme = true;
    	        	break;
            	}
            	if(finProgramme == false) {
            		choixUserMenuPrincipal = m.afficherMenu();
            	}
            }else {
            	choixUserMenuSecondaire = m.afficherSousMenu(choixUserMenuPrincipal);
            	switch(choixUserMenuPrincipal) {
    	        	case 0:
    	        		switch(choixUserMenuPrincipal) {
    			        	case 0:
    			        		System.out.println("Menu réservation");
    			        		//TODO
    			        	break;
    			        	case 1:
    			        		System.out.println("Vue réservations");
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
    	        		}
    	        	break;
    	        	case 1:
    	        		switch(choixUserMenuPrincipal) {
    			        	case 0:
    			        		System.out.println("Modification informations personnelles");
    			        		//TODO
    			        	break;
    			        	case 1:
    			        		System.out.println("Visualisation informations personnelles");
    			        		//TODO
    			        	break;
    			        	case 2:
    			        		System.out.println("Visualisation dépenses");
    			        		//TODO
    			        	break;
    	        		}
    	        	break;
    	        	case 2:
    	        		switch(choixUserMenuPrincipal) {
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
    		        	}
    	        	break;
    	        	case 3:
    	        		finProgramme = true;
    	        	break;
            	}
            	if(finProgramme == false) {
            		choixUserMenuPrincipal = m.afficherMenu();
            	}
            }
        }
        
    }
       
}
