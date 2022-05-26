package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.VehiculeAssociation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculeAssociationController {
    private DatabaseController bdd;

    public VehiculeAssociationController(DatabaseController bd) {
        bdd = bd;
    }
    VehiculeAssociation vehiculeAssociation = new VehiculeAssociation();
    public void enregisterMatricule(String immatriculation, int idCompte){
        try{
            vehiculeAssociation.enregistrerPlaque(bdd,immatriculation,idCompte);
            System.out.println("l'immatriculation est bien enregistrée!");
        }
        catch(Exception ex){
            System.out.println(ex);
        }

    }
    public void supprimerMatricule(String immatriculation){
        try {
            vehiculeAssociation.supprimerPlaque(bdd, immatriculation);
            System.out.println("l'immatriculation est bien enregistrée!");
        }
        catch(Exception ex){
            System.out.println(ex);
        }
    }
}
