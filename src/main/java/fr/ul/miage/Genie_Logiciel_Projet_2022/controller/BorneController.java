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
    
    public String consulterEtatBorne() throws SQLException {
        StringBuilder res = new StringBuilder();
        ArrayList<Borne> bornes = Borne.getAllBorne(bdd);
        for(Borne borne : bornes){
            res.append("Borne ").append(borne.getIdBorne()).append(": "+borne.getEtatBorne()+"\n");
        }
        return res.toString();
    }

    public String consulterDisponibiliteBorne() throws SQLException {
        StringBuilder res = new StringBuilder();
        ArrayList<Borne> bornes = Borne.getAllBorne(bdd);
        for(Borne borne : bornes){
            if(borne.getEtatBorne().equals("Disponible")){
                res.append("Borne ").append(borne.getIdBorne()).append(": "+borne.getEtatBorne()+"\n");
            }
        }
        if(res.isEmpty()){
            res.append("Aucune borne disponible");
        }
        return res.toString();
    }
}
