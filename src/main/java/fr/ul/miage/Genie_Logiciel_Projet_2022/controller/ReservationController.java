package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;


import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Borne;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Reservation;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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

    public Reservation creerReservation(String dateDebut, String dateFin, int idCompte, boolean estPermanente) throws SQLException, ParseException {
        ArrayList<Borne> bornesDispo = Borne.getAllBorneByEtat(bdd, "Disponible");
        ArrayList<Reservation> reservationsPendantDate = Reservation.getAllReservationBetweenDates(bdd, dateDebut, dateFin);
        ArrayList<Borne> bornesASupprimer = new ArrayList<>();

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDateDebut;
        Date newDateFin;
        try {
            newDateDebut = format.parse(dateDebut);
            newDateFin = format.parse(dateFin);
        } catch (Exception e) {
            return null;
        }
        for(Reservation reservation : reservationsPendantDate) {
            Date dateDebutReservation = format.parse(reservation.getDateDebut());
            Date dateFinReservation = format.parse(reservation.getDateFin());
            if((newDateDebut.before(dateDebutReservation)|| newDateDebut.equals(dateDebutReservation)) && (newDateFin.equals(dateDebutReservation) || newDateFin.after(dateDebutReservation))) { // vert
                return null;
            }
            else if((newDateDebut.after(dateDebutReservation) || newDateDebut.equals(dateDebutReservation)) && (newDateFin.before(dateFinReservation) || newDateFin.equals(dateFinReservation))) { // rouge
                return null;
            }
            else if((newDateDebut.before(dateFinReservation) || newDateDebut.equals(dateFinReservation)) && (newDateFin.equals(dateFinReservation) || newDateFin.after(dateFinReservation))){ // bleu
                return null;
            }
            else if((newDateDebut.before(dateDebutReservation) || newDateDebut.equals(dateDebutReservation)) && (newDateFin.after(dateFinReservation) || newDateFin.equals(dateFinReservation))){ // jaune
                return null;
            }
        }

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

    public Reservation modifierReservation(int idReservation,String dateDebut, String dateFin, int idCompte, boolean estPermanent) throws SQLException, ParseException {
        ArrayList<Borne> bornesDispo = Borne.getAllBorneByEtat(bdd, "Disponible");
        ArrayList<Reservation> reservationsPendantDate = new ArrayList<>();
        try {
            reservationsPendantDate = Reservation.getAllReservationBetweenDates(bdd, dateDebut, dateFin);
        } catch (Exception e) {
            return null;
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date newDateDebut;
        Date newDateFin;
        try {
            newDateDebut = format.parse(dateDebut);
            newDateFin = format.parse(dateFin);
        } catch (Exception e) {
            return null;
        }
        for(Reservation reservation : reservationsPendantDate) {
            if(reservation.getIdReservation() != idReservation){
                Date dateDebutReservation = format.parse(reservation.getDateDebut());
                Date dateFinReservation = format.parse(reservation.getDateFin());
                if((newDateDebut.before(dateDebutReservation)|| newDateDebut.equals(dateDebutReservation)) && (newDateFin.equals(dateDebutReservation) || newDateFin.after(dateDebutReservation))) { // vert
                    return null;
                }
                else if((newDateDebut.after(dateDebutReservation) || newDateDebut.equals(dateDebutReservation)) && (newDateFin.before(dateFinReservation) || newDateFin.equals(dateFinReservation))) { // rouge
                    return null;
                }
                else if((newDateDebut.before(dateFinReservation) || newDateDebut.equals(dateFinReservation)) && (newDateFin.equals(dateFinReservation) || newDateFin.after(dateFinReservation))){ // bleu
                    return null;
                }
                else if((newDateDebut.before(dateDebutReservation) || newDateDebut.equals(dateDebutReservation)) && (newDateFin.after(dateFinReservation) || newDateFin.equals(dateFinReservation))){ // jaune
                    return null;
                }
            }
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
