package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;

import java.sql.SQLException;
import java.util.ArrayList;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Reservation;

public class ReservationController {
	
	private DatabaseController bdd;
	
	public ReservationController(DatabaseController bd) {
		bdd = bd;
	}
	
	public String consulterReservationParUtilisateur(int idCompte) throws SQLException {
		StringBuilder res = new StringBuilder();
		ArrayList<Reservation> listeReserv = Reservation.getReservationbyUser(bdd, idCompte);
		for(Reservation r:listeReserv) {
			res.append("Réservation : ").append(r.getIdReservation()+", créneau : "+r.getDateDebut()+" - "+r.getDateFin()+", sur la borne "+r.getIdBorne());
		}
		return res.toString();
	}
	
}
