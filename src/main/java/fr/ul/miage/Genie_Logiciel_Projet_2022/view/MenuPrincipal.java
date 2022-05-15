package fr.ul.miage.Genie_Logiciel_Projet_2022.view;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;

public class MenuPrincipal implements Menu {

	private ArrayList<ArrayList<String>> listeMenus;
	private ArrayList<ArrayList<String>> listeSousMenus;
	private Compte user;
	//Ce booléen permet de savoir quel menu afficher lors de l'affichage. Sa valeur est définie dans le constructeur.
	private boolean afficherMenuConnexion;

	public MenuPrincipal(Compte user, ArrayList<ArrayList<String>> listeMenus, ArrayList<ArrayList<String>> listeSousMenus) {
		this.listeMenus = listeMenus;
		this.listeSousMenus = listeSousMenus;
		this.user = user;
		if(this.user == null) {
			this.afficherMenuConnexion = false;
		}
	}
	
	
	public int afficherMenu() {
		Scanner sc = new Scanner(System.in);
		String affichage = "";
		int choix = this.listeMenus.get(0).size();
		boolean erreur;
		affichage += "===============================\n"+
				"Bienvenue, que voulez vous faire ?\n";
		if(this.user == null) {
			for(int i =0; i<this.listeMenus.get(0).size(); i++){
				affichage += " - "+i+" - "+this.listeMenus.get(0).get(i)+"\n";
			}
			affichage += "===============================\n";
			System.out.println(affichage);
			erreur = true;
			while(erreur) {
				try {
					choix = sc.nextInt();
					erreur = false;
					while(choix > this.listeMenus.get(0).size()-1 || choix < 0) {
						System.out.println("Choix invalide, merci de recommencer.\n");
						choix = sc.nextInt();
					}
				}catch(Exception e) {
					System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
			        sc = new Scanner(System.in); 
				}
			}	
		}else {
			for(int i =0; i<this.listeMenus.get(1).size(); i++) {
				affichage += " - "+i+" - "+this.listeMenus.get(1).get(i)+"\n";
			}
			affichage += "===============================\n";
			System.out.println(affichage);
			erreur = true;
			while(erreur) {
				try {
					choix = sc.nextInt();
					erreur = false;
					while(choix > this.listeMenus.get(1).size()-1 || choix < 0) {
						System.out.println("Choix invalide, merci de recommencer.\n");
						choix = sc.nextInt();
					}
				}catch(Exception e) {
					System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
					sc = new Scanner(System.in); 
				}
			}
		}
		return choix;
	}
	
	
	
	public int afficherSousMenu(int menuChoisi) {
		Scanner sc = new Scanner(System.in);
		int choix = -1 ;
		boolean erreur;
		System.out.println(this.listeMenus.get(1).get(menuChoisi)+" :\n");
		for(int i = 0; i<this.listeSousMenus.get(menuChoisi).size();i++){
			System.out.println(" - "+i+" - "+this.listeSousMenus.get(menuChoisi).get(i)+"\n");
		}
		erreur = true;
		while(erreur) {
			try {
				choix = sc.nextInt();
				erreur = false;
				while(choix > this.listeSousMenus.get(menuChoisi).size()-1 || choix < 0) {
					System.out.println("Choix invalide, merci de recommencer.\n");
					choix = sc.nextInt();
				}
			}catch(Exception e) {
				System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
				sc = new Scanner(System.in); 
			}
		}
		return choix;
	}
	
	
	public String[] afficherMenuConnexion() {
		String[] resultat = new String[2];
		Scanner sc = new Scanner(System.in);
		String email = "";
		String mdp = "";
		System.out.println("\nConnexion\nSaisissez votre email\n");
		email = sc.nextLine();
		System.out.println("\nSaisissez votre mot de passe\n");
		mdp = sc.nextLine();
		resultat[0] = email;
		resultat[1] = mdp;
		return resultat;
	}
	
	public String[] afficherMenuInscription() {
		String[] resultat = new String[5];
		Scanner sc = new Scanner(System.in);
		String email = "";
		String mdp = "";
		String nom = "";
		String prenom = "";
		String age = "";
		
		System.out.println("\nInscription\nSaisissez votre email\n");
		email = sc.nextLine();
		System.out.println("\nSaisissez votre mot de passe\n");
		mdp = sc.nextLine();
		System.out.println("\nSaisissez votre nom\n");
		nom = sc.nextLine();
		System.out.println("\nSaisissez votre prénom\n");
		prenom = sc.nextLine();
		System.out.println("\nSaisissez votre âge\n");
		age = sc.nextLine();
		resultat[0] = email;
		resultat[1] = mdp;
		resultat[2] = nom;
		resultat[3] = prenom;
		resultat[4] = age;
		return resultat;
	}
	
	

	public ArrayList<ArrayList<String>> getListeMenus() {
		return listeMenus;
	}
	
	public ArrayList<ArrayList<String>> getListeSousMenus() {
		return listeSousMenus;
	}
	
}
