import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.*;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.HistoriqueDepense;
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
		bdd.connexionDatabase();

        ArrayList<Compte> comptes = bdd.getAllClient();
        BorneController borneController = new BorneController(bdd);
		CompteController compteController = new CompteController(bdd);
        for(Compte compte : comptes){
            System.out.println(compte);
        }


		VehiculeAssociationController vehiculeAssociationController = new VehiculeAssociationController(bdd);

		HistoriqueDepenseController historiqueDepenseController = new HistoriqueDepenseController(bdd);

		StatistiqueController statistiqueController = new StatistiqueController();

		VariableController variableController = new VariableController(bdd);


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
        listeSousMenus2.add("Modifier informations");
        listeSousMenus2.add("Visualiser informations");
        listeSousMenus2.add("Visualiser dépenses");
		listeSousMenus2.add("Quitter");
       
        ArrayList<String> listeSousMenus3 = new ArrayList<String>();
        listeSousMenus3.add("Visualiser immatriculations");
        listeSousMenus3.add("Ajouter immatriculation");
        listeSousMenus3.add("Supprimer immatriculation");
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
						c=compteController.inscrire(0,informationInscription[0],informationInscription[1],informationInscription[2],informationInscription[3],
								Integer.parseInt(informationInscription[4]),true,false,false);
    	        		break;

    	        	case 1:
						//menu connexion
						String [] information = m.afficherMenuConnexion();
						c=compteController.seConnecter(information[0], information[1]);
						System.out.println(c);
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
    			        		System.out.println("Vue réservations");
    			        		//TODO
    			        		break;
    			        	case 2:
    			        		System.out.println("Modification réservation");
    			        		//TODO
    			        		break;
    			        	case 3:
    			        		System.out.println("Disponibilité bornes");
    			        		//Afficher quantitée des bornes diponibles
								borneController.getQteBorneDisponible();
    			        		break;
							case 4:
								break;
    	        		}
    	        		break;
    	        	case 1:
    	        		switch(choixUserMenuSecondaire) {
    			        	case 0:
    			        		System.out.println("Modification informations personnelles");
    			        		//TODO
    			        		break;
    			        	case 1:
    			        		System.out.println("Visualisation informations personnelles");
    			        		//TODO
    			        		break;
    			        	case 2:
								// permet de voir les dépenses par client
    			        		System.out.println("Visualisation dépenses");
								historiqueDepenseController.getDepenseByClient(c.getIdCompte());
    			        		break;
							case 3:
								break;
    	        		}
    	        		break;
    	        	case 2:
    	        		switch(choixUserMenuSecondaire) {
    			        	case 0:
    			        		System.out.println("Visualiser immatriculations");
    			        		//TODO
    			        		break;
    			        	case 1:
								//Enregistrement d'immatricule
    			        		System.out.println("Ajouter immatriculation\n");
								Scanner sc = new Scanner(System.in);
								String matricule;
								System.out.println("Entrez l'immatricule: ");
								matricule = sc.nextLine();
								vehiculeAssociationController.enregisterMatricule(matricule,c.getIdCompte());
    			        		break;
    			        	case 2:
								//Suppression d'immatricule
    			        		System.out.println("Supprimer immatriculation");
								sc = new Scanner(System.in);
								System.out.println("Entrez l'immatricule: ");
								matricule = sc.nextLine();
								vehiculeAssociationController.supprimerMatricule(matricule);

    			        		break;
							case 3:
								break;
    		        	}
    	        		break;
    	        	case 3:
						if(m.getUser().isEstGerant()){
							System.out.println("\n======Menu administrateur======\n");
							System.out.println("\n 1-consulter statistiques \n");
							System.out.println("\n 2-Fixer les prix des services \n");
							switch (choixUserMenuSecondaire){
								case 1: {
									// affichage les statistiques
									statistiqueController.getStatistiques(bdd);
								}
								break;
								case 2:{
									//Menu Fixer les prix des services
									System.out.println("\n======Fixer les prix des services======\n");
									System.out.println("\n 1-Fixer le prix de demi heure \n");
									System.out.println("\n 2-Fixer le prix de demi heure surtaxée \n");
									System.out.println("\n 3-Fixer le nombre des bornes de bornes \n");
									switch (choixUserMenuSecondaire){
										case 1: {
											Scanner sc = new Scanner(System.in);
											Double nouveauPrix;
											System.out.println("\n Entrez le nouveau prix ex: 15.00 \n");
											nouveauPrix = sc.nextDouble();
											variableController.updatePrixDemiHeure(nouveauPrix);
										}
										break;
										case 2:{
											Scanner s = new Scanner(System.in);
											Double nouveauPrixSurtaxe;
											System.out.println("\n Entrez le nouveau prix ex: 16.00 \n");
											nouveauPrixSurtaxe = s.nextDouble();
											variableController.updatePrixDemiHeureSurtaxe(nouveauPrixSurtaxe);
										}
										break;
										case 3:{
											Scanner scanner = new Scanner(System.in);
											int nouveauQteBorne;
											System.out.println("\n Entrez le nouveau quantite des bornes ex: 16 \n");
											nouveauQteBorne = scanner.nextInt();
											variableController.updatequantiteBorne(nouveauQteBorne);
										}
										break;
									}
									break;

								}

							}
						}
						else {
							c = null;
							m.setUser(c);
							break;
						}
					case 4:
						if(m.getUser().isEstGerant()){
							c = null;
							m.setUser(c);
							break;
						}else {

						}
            	}
            }
        }
        

    }

}
