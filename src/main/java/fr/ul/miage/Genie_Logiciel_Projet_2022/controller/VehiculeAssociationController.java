package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Vehicule;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.VehiculeAssociation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculeAssociationController {
    private DatabaseController bdd;
    private VehiculeAssociation vehiculeAssociation;

    public VehiculeAssociationController(DatabaseController bd) {
        bdd = bd;
        vehiculeAssociation = new VehiculeAssociation();
    }

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

    public VehiculeAssociation creerAssociation(String immatriculation, int idCompte) throws SQLException {
        ArrayList<VehiculeAssociation> associations = VehiculeAssociation.getAssociationByImmatriculation(bdd, immatriculation);
        VehiculeAssociation association = null;
        // Pas d'immatriculation ?
        if(associations.isEmpty()){
            if(Vehicule.getVehiculeByImmatriculation(bdd, immatriculation) != null){
                association = VehiculeAssociation.insertNewAssociation(bdd, immatriculation, idCompte, "Permanent");
            }
        } else {
            for(VehiculeAssociation asso : associations){
                if(asso.getIdCompte() == idCompte){
                    association = VehiculeAssociation.getAssociationByImmatriculationAndIdCompte(bdd, immatriculation, idCompte);
                } else {
                    association = VehiculeAssociation.insertNewAssociation(bdd, immatriculation, idCompte, "Temporaire");
                }
            }
        }
        return association;
    }
}
