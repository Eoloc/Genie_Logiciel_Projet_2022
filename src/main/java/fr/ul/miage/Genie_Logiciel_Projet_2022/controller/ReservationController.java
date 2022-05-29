package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;


import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Borne;
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
			res.append("Réservation : ").append(r.getIdReservation()+", créneau : "+r.getDateDebut()+" - "+r.getDateFin()+", sur la borne "+r.getIdBorne() + "\n");
		}
		return res.toString();
	}

    public Reservation creerReservation(String dateDebut, String dateFin, int idCompte, boolean estPermanente) throws SQLException {
        ArrayList<Borne> bornesDispo = Borne.getAllBorneByEtat(bdd, "Disponible");
        ArrayList<Reservation> reservationsPendantDate = Reservation.getAllReservationBetweenDates(bdd, dateDebut, dateFin);
        ArrayList<Borne> bornesASupprimer = new ArrayList<>();

        for(Borne borne : bornesDispo){
            for(Reservation reservation : reservationsPendantDate){
                if(borne.getIdBorne() == reservation.getIdBorne()){
                    bornesASupprimer.add(borne);
                    break;
                }
            }
        }
        for(Borne borneDejaPrise : bornesASupprimer){
            bornesDispo.remove(borneDejaPrise);
        }
        if(bornesDispo.size() == 0){
            return null;
        }

        Borne borne = bornesDispo.get(0);
        return Reservation.insertNewReservation(bdd, dateDebut, dateFin, "En attente", idCompte, borne.getIdBorne(), estPermanente);
    }

    public Boolean supprimerReservation(int idReservation) throws SQLException {
        Reservation reservation = Reservation.getReservationbyId(bdd, idReservation);
        return reservation.deleteReservation(bdd, reservation);
    }

    public Reservation modifierReservation(int idReservation,String dateDebut, String dateFin, int idCompte, boolean estPermanent) throws SQLException {
        ArrayList<Borne> bornesDispo = Borne.getAllBorneByEtat(bdd, "Disponible");
        ArrayList<Reservation> reservationsPendantDate = new ArrayList<>();
        try {
            reservationsPendantDate = Reservation.getAllReservationBetweenDates(bdd, dateDebut, dateFin);
        } catch (Exception e) {
            return null;
        }

        ArrayList<Borne> bornesASupprimer = new ArrayList<>();

        for(Borne borne : bornesDispo){
            for(Reservation reservation : reservationsPendantDate){
                if(borne.getIdBorne() == reservation.getIdBorne()){
                    bornesASupprimer.add(borne);
                    break;
                }
            }
        }
        for(Borne borneDejaPrise : bornesASupprimer){
            bornesDispo.remove(borneDejaPrise);
        }
        if(bornesDispo.size() == 0){
            return null;
        }

        Borne borne = bornesDispo.get(0);
        try {
            if(Reservation.getReservationbyId(bdd, idReservation).getIdCompte() != idCompte) {
                return null;
            }
        } catch(Exception e){
            return null;
        }
        try {
            if (Reservation.updateReservation(bdd, idReservation, dateDebut, dateFin, estPermanent, borne.getIdBorne())) {
                return Reservation.getReservationbyId(bdd, idReservation);
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
