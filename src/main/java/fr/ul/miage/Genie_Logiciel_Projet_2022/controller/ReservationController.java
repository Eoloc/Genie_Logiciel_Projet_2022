package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;


import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Borne;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationController {
    private DatabaseController bdd;

    public ReservationController(DatabaseController bd) { bdd = bd; }

    public String consulterReservationParUtilisateur(int idCompte) throws SQLException {
		StringBuilder res = new StringBuilder();
		ArrayList<Reservation> listeReserv = Reservation.getReservationbyUser(bdd, idCompte);
		for(Reservation r:listeReserv) {
			res.append("Réservation : ").append(r.getIdReservation()+", créneau : "+r.getDateDebut()+" - "+r.getDateFin()+", sur la borne "+r.getIdBorne());
		}
		return res.toString();
	}

    public Reservation creerReservation(DatabaseController bdd, String dateDebut, String dateFin, int idCompte, boolean estPermanente) throws SQLException {
        ArrayList<Borne> bornesDispo = Borne.getAllBorneByEtat(bdd, "Disponible");
        if(bornesDispo.size() == 0){
            return null;
        }
        Borne borne = bornesDispo.get(0);
        return Reservation.insertNewReservation(bdd, dateDebut, dateFin, "En attente", idCompte, borne.getIdBorne(), estPermanente);
    }

    public String consulterReservationsParBorne() throws SQLException {
    	StringBuilder res = new StringBuilder();
        ArrayList<Borne> bornes = Borne.getAllBorne(bdd);
        for(Borne borne : bornes){
        	res.append("Borne "+borne.getIdBorne()+" :");
        	ArrayList<Reservation> reservations = Reservation.getReservationbyIdBorne(bdd,borne.getIdBorne());
        	if(reservations.isEmpty()) {
        		res.append(" Aucune réservation\n");
        	}else {
        		for(Reservation reservation : reservations) {
            		res.append("\n"+"Date début: "+reservation.getDateDebut()).append("Date fin: "+reservation.getDateFin()).append("Etat réservation: "+reservation.getEtatReservation()).append("Permanente: "+reservation.getEstPermanente()).append("Id client: "+reservation.getIdBorne()).append("Id borne: "+reservation.getIdBorne()+"\n");
            	}
        	}
        }
        return res.toString();
    }
    
    
    public Boolean supprimerReservation(DatabaseController bdd, int idReservation) throws SQLException {
        Reservation reservation = Reservation.getReservationbyId(bdd, idReservation);
        return reservation.deleteReservation(bdd, reservation);
    }
}
