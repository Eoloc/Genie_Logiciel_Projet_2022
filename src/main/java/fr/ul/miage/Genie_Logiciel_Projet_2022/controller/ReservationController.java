package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Borne;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Reservation;

import java.sql.SQLException;
import java.util.ArrayList;

public class ReservationController {
    private DatabaseController bdd;

    public ReservationController(DatabaseController bd) { bdd = bd; }

    public Reservation creerReservation(DatabaseController bdd, String dateDebut, String dateFin, int idCompte, boolean estPermanente) throws SQLException {
        ArrayList<Borne> bornesDispo = Borne.getAllBorneByEtat(bdd, "Disponible");
        if(bornesDispo.size() == 0){
            return null;
        }
        Borne borne = bornesDispo.get(0);
        return Reservation.insertNewReservation(bdd, dateDebut, dateFin, "En attente", idCompte, borne.getIdBorne(), estPermanente);
    }

    public Boolean supprimerReservation(DatabaseController bdd, int idReservation) throws SQLException {
        Reservation reservation = Reservation.getReservationbyId(bdd, idReservation);
        return reservation.deleteReservation(bdd, reservation);
    }
}
