package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Borne;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;

import java.sql.*;
import java.util.ArrayList;

public class BorneController {
    private DatabaseController bdd;

    public BorneController(DatabaseController bd) {
        bdd = bd;
    }

    public String consulterBorne() throws SQLException {
        StringBuilder res = new StringBuilder();
        ArrayList<Borne> bornes = Borne.getAllBorne(bdd);
        for(Borne borne : bornes){
            if(borne.getEtatBorne().equals("Disponible")){
                res.append("Borne ").append(borne.getIdBorne()).append(" : Disponible\n");
            }
        }
        return res.toString();
    }

    public void getQteBorneDisponible() throws SQLException {
        Borne borne = new Borne();
        int quantite= 0;
        quantite=borne.getQteBorneDisponible(bdd);
        System.out.println("La quantité des bornes disponibles est :" + quantite +" Borne");
    }


}
