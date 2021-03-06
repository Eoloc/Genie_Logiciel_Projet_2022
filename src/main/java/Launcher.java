import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.*;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Reservation;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.VehiculeAssociation;
import fr.ul.miage.Genie_Logiciel_Projet_2022.view.MenuPrincipal;
import org.ini4j.Ini;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Scanner;


public class Launcher {
    public static void main(String[] args) throws SQLException, ParseException {
		Ini ini = null;
		try {
			ini = new Ini(new File("src/main/java/bdd.ini"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		assert ini != null;
		DatabaseController bdd = new DatabaseController(ini.get("database", "user"), ini.get("database", "password"), ini.get("database", "port"));
		bdd.connexionDatabase();

        ArrayList<Compte> comptes = bdd.getAllClient();
        BorneController borneController = new BorneController(bdd);
		CompteController compteController = new CompteController(bdd);
        ReservationController reservationController = new ReservationController(bdd);
		VehiculeAssociationController vehiculeAssociationController = new VehiculeAssociationController(bdd);
        for(Compte compte : comptes){
            System.out.println(compte);
        }

        
        
        //Menu d'accueil
        ArrayList<String> listeMenusAccueil = new ArrayList<String>();
        listeMenusAccueil.add("Inscription");
        listeMenusAccueil.add("Connexion");
        listeMenusAccueil.add("Quitter");
        
        
        //Création des menus des utilisateurs
        //menu principal utilisateurs
        ArrayList<String> listeMenusPrincipalUsers = new ArrayList<String>();
        listeMenusPrincipalUsers.add("Gestion réservation");
        listeMenusPrincipalUsers.add("Gestion immatriculations");
        listeMenusPrincipalUsers.add("Gestion profil");
        listeMenusPrincipalUsers.add("Quitter");
        
        //sous-menus utilisateurs
        ArrayList<ArrayList<String>> listeSousMenusUsers = new ArrayList<ArrayList<String>>();
        //gestion réservations
        ArrayList<String> listeSousMenus1 = new ArrayList<String>();
        listeSousMenus1.add("Passer réservation");
        listeSousMenus1.add("Visualiser réservations");
		listeSousMenus1.add("Supprimer réservations");
        listeSousMenus1.add("Modifier réservations");
        listeSousMenus1.add("Voir disponibilités bornes");
		listeSousMenus1.add("Quitter");
             
		//gestion immatriculations
        ArrayList<String> listeSousMenus2 = new ArrayList<String>();
        listeSousMenus2.add("Visualiser immatriculations");
        listeSousMenus2.add("Ajouter immatriculation");
        listeSousMenus2.add("Supprimer immatriculation");
        listeSousMenus2.add("Quitter");

        //gestion profil
        ArrayList<String> listeSousMenus3 = new ArrayList<String>();
        listeSousMenus3.add("Modifier informations");
        listeSousMenus3.add("Visualiser informations");
        listeSousMenus3.add("Visualiser dépenses");
        listeSousMenus3.add("Quitter");
        
        listeSousMenusUsers.add(listeSousMenus1);
        listeSousMenusUsers.add(listeSousMenus2);
        listeSousMenusUsers.add(listeSousMenus3);
                

        //Création des menus des administrateurs/gérants
        //menu principal administrateurs/gérants
        ArrayList<String> listeMenusPrincipalAdmins = new ArrayList<String>();
        listeMenusPrincipalAdmins.add("Gestion bornes");
        listeMenusPrincipalAdmins.add("Gestion utilisateurs et réservations");
        listeMenusPrincipalAdmins.add("Gestion profil");
        listeMenusPrincipalAdmins.add("Gestion paramètres");
        listeMenusPrincipalAdmins.add("Quitter");
        
        //sous-menus administrateurs/gérants
        ArrayList<ArrayList<String>> listeSousMenusAdmins = new ArrayList<ArrayList<String>>(); 
        //gestion bornes
        ArrayList<String> listeSousMenusAdmins1 = new ArrayList<String>();
        listeSousMenusAdmins1.add("Consulter états bornes");
        listeSousMenusAdmins1.add("Visualiser réservations par borne");
		listeSousMenusAdmins1.add("Consulter disponibilités bornes");
        listeSousMenusAdmins1.add("Quitter");
        
        //gestion utilisateurs et réservations
        ArrayList<String> listeSousMenusAdmins2 = new ArrayList<String>();
        listeSousMenusAdmins2.add("Consulter profils utilisateurs");
        listeSousMenusAdmins2.add("Quitter");

        //gestion profil
        ArrayList<String> listeSousMenusAdmins3 = new ArrayList<String>();
        listeSousMenusAdmins3.add("Modifier informations");
        listeSousMenusAdmins3.add("Visualiser informations");
        listeSousMenusAdmins3.add("Quitter");
        
        listeSousMenusAdmins.add(listeSousMenusAdmins1);
        listeSousMenusAdmins.add(listeSousMenusAdmins2);
        listeSousMenusAdmins.add(listeSousMenusAdmins3);
        
        Compte c = null;
        MenuPrincipal m = new MenuPrincipal(c,listeMenusAccueil,listeMenusPrincipalUsers,listeSousMenusUsers,listeMenusPrincipalAdmins,listeSousMenusAdmins);
        int choixUserMenuPrincipal = -1;
        int choixUserMenuSecondaire;
        boolean finProgramme = false;    
        while(!finProgramme) {
        	if(c == null) {
				choixUserMenuPrincipal = m.afficherMenu();
            	switch(choixUserMenuPrincipal) {
    	        	case 0:
						String [] informationInscription = m.afficherMenuInscription();
						c=compteController.inscrire(0,informationInscription[0],informationInscription[1],informationInscription[2],informationInscription[3],
								Integer.parseInt(informationInscription[4]),true,false,false);
    	        		break;
    	        	case 1:
						//menu connexion
						String [] information = m.afficherMenuConnexion();
						c=compteController.seConnecter(information[0], information[1]);

						if(c != null){
							m.setUser(c);
						}else {
							System.out.println("Connexion impossible\n");
						}
						break;
    	        	case 2:
    	        		finProgramme = true;
    	        		break;
            	}
            } else {
				choixUserMenuPrincipal = m.afficherMenu();
				choixUserMenuSecondaire = -1;
            	choixUserMenuSecondaire = m.afficherSousMenu(choixUserMenuPrincipal);
            	switch(choixUserMenuPrincipal) {
    	        	case 0: // Gestion Réservation / borne
    	        		switch(choixUserMenuSecondaire) {
    			        	case 0: // Créer réservation / consulter états bornes
    			        		if(c.isEstGerant()) {
    			        			String listeBornes = borneController.consulterEtatBorne();
    			        			m.afficherEtatBornes(listeBornes);
    			        		} else {
									boolean erreur = true;
									while (erreur){
										String[] resultat = m.afficherCreerReservation();
										boolean estPerma = resultat[2].equals("0");
										VehiculeAssociation association = vehiculeAssociationController.creerAssociation(resultat[3], c.getIdCompte());
										if(association == null){
											System.out.println("Aucun véhicule trouvé pour l'immatriculation : " + resultat[3]);
										} else {
											try{
												Reservation newReservation = reservationController.creerReservation(resultat[0], resultat[1], c.getIdCompte(), estPerma);
												erreur = false;
												if(newReservation == null){
													System.out.println("Impossible de créer la réservation");
												} else {
													System.out.println("Réservation créée");
												}
											} catch (Exception e) {
												e.printStackTrace();
												System.out.println("Erreur de saisie dans les dates");
											}
										}
									}
    			        		}
    			        		break;
    			        	case 1: // Consulter toutes les réservations / Ses réservations

    			        		if(c.isEstGerant()) {

    			        		} else {
    			        			//Liste réservations 
    			        			String listeReserv = reservationController.consulterReservationParUtilisateur(c.getIdCompte());
        			        		m.afficherListeReservations(listeReserv);
    			        		}
    			        		break;
							case 2: // Supprimer réservation /
								if(c.isEstGerant()) {
									System.out.println(borneController.consulterDisponibiliteBorne());
								} else {
									int idReservationSupprimer = m.afficherSupprimerReservation();
									Reservation reservationASuppr = Reservation.getReservationbyId(bdd, idReservationSupprimer);
									if(reservationASuppr.getIdCompte() == c.getIdCompte()){
										if(reservationController.supprimerReservation(idReservationSupprimer)){
											System.out.println("Reservation bien supprimée");
										} else {
											System.out.println("Aucune réservation trouvée sous le numéro : " + idReservationSupprimer);
										}
									} else {
										System.out.println("Cette réservation n'est pas a vous");
									}
								}
								break;
    			        	case 3: // Modification réservation
    			        		if(!c.isEstGerant()) {
    			        			String[] resultats = m.afficherModifierReservation();
									boolean estPermanente = resultats[3].equals("0");
									if(reservationController.modifierReservation(Integer.parseInt(resultats[0]), resultats[1], resultats[2], c.getIdCompte(), estPermanente) == null){
										System.out.println("Erreur impossible de mettre à jour cette réservation");
									} else {
										System.out.println("Réservation modifiée");
									}
    			        		}
    			        		break;
    			        	case 4: // Disponibilité bornes
    			        		if(!c.isEstGerant()) {
									System.out.println(borneController.consulterDisponibiliteBorne());
    			        		}
    			        		break;
							case 5:
								break;
    	        		}
    	        		break;
    	        	case 1: // Gestion immatriculation
    	        		switch(choixUserMenuSecondaire) {
			        	case 0:
			        		System.out.println("Visualiser immatriculations");
			        		if(c.isEstGerant()) {
			        			
			        		}else {
			        			
			        		}
			        		//TODO
			        		break;
			        	case 1:
			        		System.out.println("Ajouter immatriculation");
			        		if(c.isEstGerant()) {
			        			
			        		}else {
			        			
			        		}
			        		//TODO
			        		break;
			        	case 2:
			        		System.out.println("Supprimer immatriculation");
			        		if(c.isEstGerant()) {
			        			
			        		}else {
			        			
			        		}
			        		//TODO
			        		break;
						case 3:
							break;
		        	}
    	        		break;
    	        	case 2: // Gestion informations personnelles
    	        		switch(choixUserMenuSecondaire) {
			        	case 0:
			        		//modification informations personnelles
		        			Compte cModifie = m.afficherModificationInformationsPersonnelles();
			        		c.setEmail(cModifie.getEmail());
			        		c.setMdp(cModifie.getMdp());
			        		c.setNom(cModifie.getNom());
			        		c.setPrenom(cModifie.getPrenom());
			        		c.setAge(cModifie.getAge());
				        	c.updateCompte(bdd);
			        		break;
			        	case 1:
			        		//afficher informations personnelles
			        		m.afficherInformationsPersonnelles();
			        		break;
			        	case 2:
			        		System.out.println("Visualisation dépenses");
			        		if(c.isEstGerant()) {
			        			
			        		}else {
			        			
			        		}
			        		//TODO
			        		break;
						case 3:
							break;
	        		}
    	        		break;
    	        	case 3:
    	        		if(!c.isEstGerant()) {
    	        			c = null;
    						m.setUser(c);
        	        		break;
    	        		}
    	        	case 4:
    	        		if(c.isEstGerant()) {
    	        			c = null;
    						m.setUser(c);
        	        		break;  	        
    	        		}
            	}
            }
        }
    }
}
