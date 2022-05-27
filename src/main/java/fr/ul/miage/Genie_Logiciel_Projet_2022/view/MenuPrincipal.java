package fr.ul.miage.Genie_Logiciel_Projet_2022.view;

import java.util.ArrayList;
import java.util.Scanner;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Borne;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Reservation;

public class MenuPrincipal implements Menu {

	private ArrayList<String> listeMenusAccueil;
	private ArrayList<String> listeMenusPrincipauxUsers;
	private ArrayList<ArrayList<String>> listeSousMenusUsers;
	private ArrayList<String> listeMenusPrincipauxGerants;
	private ArrayList<ArrayList<String>> listeSousMenusGerants;
	private Compte user;
	//Ce booléen permet de savoir quel menu afficher lors de l'affichage. Sa valeur est définie dans le constructeur.
	private boolean afficherMenuConnexion;

	public MenuPrincipal(Compte user, ArrayList<String> listeMenusAccueil, ArrayList<String> listeMenusPrincipauxUsers, ArrayList<ArrayList<String>> listeSousMenusUsers, ArrayList<String> listeMenusPrincipauxGerants, ArrayList<ArrayList<String>> listeSousMenusGerants) {
		this.listeMenusAccueil = listeMenusAccueil;
		this.listeMenusPrincipauxUsers = listeMenusPrincipauxUsers;
		this.listeSousMenusUsers = listeSousMenusUsers;
		this.listeMenusPrincipauxGerants = listeMenusPrincipauxGerants;
		this.listeSousMenusGerants = listeSousMenusGerants;
		this.user = user;
		if(this.user == null) {
			this.afficherMenuConnexion = false;
		}
	}
	
	
	public int afficherMenu() {
		Scanner sc = new Scanner(System.in);
		String affichage = "";
		int choix = this.listeMenusAccueil.size();
		boolean erreur;
		affichage += "===============================\n"+
				"Bienvenue, que voulez vous faire ?\n";
		if(this.user == null) {
			for(int i =0; i<this.listeMenusAccueil.size(); i++){
				affichage += " - "+i+" - "+this.listeMenusAccueil.get(i)+"\n";
			}
			affichage += "===============================\n";
			System.out.println(affichage);
			erreur = true;
			while(erreur) {
				try {
					choix = sc.nextInt();
					erreur = false;
					while(choix > this.listeMenusAccueil.size()-1 || choix < 0) {
						System.out.println("Choix invalide, merci de recommencer.\n");
						choix = sc.nextInt();
					}
				}catch(Exception e) {
					System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
			        sc = new Scanner(System.in); 
				}
			}	
		}else {
			if(this.user.isEstGerant()) {
				for(int i =0; i<this.listeMenusPrincipauxGerants.size(); i++) {
					affichage += " - "+i+" - "+this.listeMenusPrincipauxGerants.get(i)+"\n";
				}
				affichage += "===============================\n";
				System.out.println(affichage);
				erreur = true;
				while(erreur) {
					try {
						choix = sc.nextInt();
						erreur = false;
						while(choix > this.listeMenusPrincipauxGerants.size()-1 || choix < 0) {
							System.out.println("Choix invalide, merci de recommencer.\n");
							choix = sc.nextInt();
						}
					}catch(Exception e) {
						System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
						sc = new Scanner(System.in); 
					}
				}
			} else {
				for(int i =0; i<this.listeMenusPrincipauxUsers.size(); i++) {
					affichage += " - "+i+" - "+this.listeMenusPrincipauxUsers.get(i)+"\n";
				}
				affichage += "===============================\n";
				System.out.println(affichage);
				erreur = true;
				while(erreur) {
					try {
						choix = sc.nextInt();
						erreur = false;
						while(choix > this.listeMenusPrincipauxUsers.size()-1 || choix < 0) {
							System.out.println("Choix invalide, merci de recommencer.\n");
							choix = sc.nextInt();
						}
					}catch(Exception e) {
						System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
						sc = new Scanner(System.in); 
					}
				}
			}
		}
		return choix;
	}
	
	
	
	public int afficherSousMenu(int menuChoisi) {
		Scanner sc = new Scanner(System.in);
		int choix = -1 ;
		boolean erreur;
		ArrayList<String> listeMenusPrincipauxAParcourir = new ArrayList<String>();
		ArrayList<ArrayList<String>> listeSousMenusAParcourir = new ArrayList<ArrayList<String>>();
		if(this.user.isEstGerant()) {
			listeMenusPrincipauxAParcourir = this.listeMenusPrincipauxGerants;
			listeSousMenusAParcourir = this.listeSousMenusGerants;
		}else {
			listeMenusPrincipauxAParcourir = this.listeMenusPrincipauxUsers;
			listeSousMenusAParcourir = this.listeSousMenusUsers;
		}
		System.out.println(listeMenusPrincipauxAParcourir.get(menuChoisi)+" :\n");
		try {
			if(listeSousMenusAParcourir.get(menuChoisi) != null){
				for(int i = 0; i<listeSousMenusAParcourir.get(menuChoisi).size();i++){
					System.out.println(" - "+i+" - "+listeSousMenusAParcourir.get(menuChoisi).get(i)+"\n");
				}
				erreur = true;
				while(erreur) {
					try {
						choix = sc.nextInt();
						erreur = false;
						while(choix > listeSousMenusAParcourir.get(menuChoisi).size()-1 || choix < 0) {
							System.out.println("Choix invalide, merci de recommencer.\n");
							choix = sc.nextInt();
						}
					}catch(Exception e) {
						System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
						sc = new Scanner(System.in);
					}
				}
			}
		}catch (Exception e) {
			
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
	
	
	public void afficherEtatBornes(String listeBornes) {
		String affichage = "===============================\n";
		affichage += listeBornes;
		affichage += "===============================\n";
		System.out.println(affichage);
	}
	
	
	
	
	public void afficherInformationsPersonnelles() {
		String affichage = "===============================\n";
		affichage += "Mes informations personnelles: \n";
		affichage += "Prénom: " + this.user.getPrenom()+"\n";
		affichage += "Nom: " + this.user.getNom()+"\n";
		affichage += "Âge: " + this.user.getAge()+"\n";
		affichage += "Email: " + this.user.getEmail()+"\n";
		affichage += "===============================\n";
		System.out.println(affichage);
	}
	
	
	public Compte afficherModificationInformationsPersonnelles() {
		Scanner sc = new Scanner(System.in);
		int choix = -1;
		String nouvelleValeur;
		int nouvelleValeurAge = this.user.getAge();
		boolean erreur = false;
		
		String affichage = "===============================\n";
		affichage += "Modification informations personnelles: \n";
		affichage += " - 0 - Prénom: " + this.user.getPrenom()+"\n";
		affichage += " - 1 - Nom: " + this.user.getNom()+"\n";
		affichage += " - 2 - Âge: " + this.user.getAge()+"\n";
		affichage += " - 3 - Email: " + this.user.getEmail()+"\n";
		affichage += " - 4 - Mot de passe\n";
		affichage += " - 5 - Quitter\n";
		affichage += "===============================\n";
		affichage += "Indiquer quelle information vous souhaitez modifier\n";
		System.out.println(affichage);
		erreur = true;
		while(erreur) {
			try {
				choix = sc.nextInt();
				while(choix < 0 || choix > 5) {
					System.out.println("Choix invalide\n");
					choix = sc.nextInt();
				}
				erreur = false;
			}catch(Exception e) {
				System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
				sc = new Scanner(System.in);
			}
		}
		sc = new Scanner(System.in);
		switch(choix) {
			case 0:
				System.out.println("Modification du prénom, renseignez la nouvelle valeur\n");
				erreur = true;
				while(erreur) {
					try {
						nouvelleValeur = sc.nextLine();
						if(nouvelleValeur != "") {
							this.user.setPrenom(nouvelleValeur);
						}
						erreur = false;
					}catch(Exception e) {
						System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
						sc = new Scanner(System.in);
					}
				}
				break;
			case 1:
				System.out.println("Modification du nom, renseignez la nouvelle valeur\n");
				erreur = true;
				while(erreur) {
					try {
						nouvelleValeur = sc.nextLine();
						if(nouvelleValeur != "") {
							this.user.setNom(nouvelleValeur);
						}
						erreur = false;
					}catch(Exception e) {
						System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
						sc = new Scanner(System.in);
					}
				}
				
				break;
			case 2:
				System.out.println("Modification de l'âge, renseignez la nouvelle valeur\n");
				erreur = true;
				while(erreur) {
					try {
						nouvelleValeurAge = sc.nextInt();
						this.user.setAge(nouvelleValeurAge);
						erreur = false;
					}catch(Exception e) {
						System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
						sc = new Scanner(System.in);
					}
				}
				break;
			case 3:
				System.out.println("Modification de l'email, renseignez la nouvelle valeur\n");
				erreur = true;
				while(erreur) {
					try {
						nouvelleValeur = sc.nextLine();
						if(nouvelleValeur != "") {
							this.user.setEmail(nouvelleValeur);
						}
						erreur = false;
					}catch(Exception e) {
						System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
						sc = new Scanner(System.in);
					}
				}
				break;
			case 4:
				System.out.println("Modification du mot de passe, renseignez votre mot de passe actuel avant d'indiquer la nouvelle valeur\n");
				erreur = true;
				while(erreur) {
					try {
						nouvelleValeur = sc.nextLine();
						if(nouvelleValeur.equals(this.user.getMdp())) {
							System.out.println("Renseinger le nouveau mot de passe\n");
							nouvelleValeur = sc.nextLine();
							if(nouvelleValeur != "") {
								this.user.setMdp(nouvelleValeur);
							}
						}else {
							System.out.println("Mot de passe incorrect\n");
						}
						erreur = false;
					}catch(Exception e) {
						System.out.println("Valeur incorrecte. Veuillez réeffectuer la saisie.");
						sc = new Scanner(System.in);
					}
				}
				
				break;
			case 5:
				break;
		}
		return this.user;
	}
	
	public void afficherListeUtilisateurs(String liste) {
		String affichage = "===============================\n";
		affichage += "Liste utilisateurs: \n";
		affichage += liste;
		affichage += "===============================\n";
		System.out.println(affichage);
	}
	
	public void afficherListeReservations(String liste) {
		String affichage = "===============================\n";
		affichage += "Mes réservations: \n";
		affichage += liste;
		affichage += "===============================\n";
		System.out.println(affichage);
	}

	

	public ArrayList<String> getListeMenusAccueil() {
		return listeMenusAccueil;
	}

	public ArrayList<String> getListeMenusPrincipauxUsers() {
		return listeMenusPrincipauxUsers;
	}

	public ArrayList<ArrayList<String>> getListeSousMenusUsers() {
		return listeSousMenusUsers;
	}

	public ArrayList<String> listeMenusPrincipauxGerants() {
		return listeMenusPrincipauxGerants;
	}

	public ArrayList<ArrayList<String>> getListeSousMenusGerants() {
		return listeSousMenusGerants;
	}

	public Compte getUser() {
		return user;
	}

	public void setUser(Compte user) {
		this.user = user;
	}
}
