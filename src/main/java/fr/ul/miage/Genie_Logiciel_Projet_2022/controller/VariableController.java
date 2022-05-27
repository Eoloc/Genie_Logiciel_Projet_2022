package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Variable;

import java.sql.SQLException;

public class VariableController {

    private DatabaseController bdd;

    public VariableController(DatabaseController bd){bdd=bd;}

    Variable variable = new Variable();

    public void updatePrixDemiHeure(double nouveauPrixDemiHeure) throws SQLException {
        variable.updatePrixDemiHeure(bdd,nouveauPrixDemiHeure);
        System.out.println("le nouveau prix demi heure est : "+nouveauPrixDemiHeure);
    }

    public void updatePrixDemiHeureSurtaxe(double nouveauPrixDemiHeureSurtaxe) throws SQLException {
        variable.updatePrixDemiHeureSurtaxe(bdd,nouveauPrixDemiHeureSurtaxe);
        System.out.println("le nouveau prix demi heure surtaxe est : "+nouveauPrixDemiHeureSurtaxe);
    }

    public void updatequantiteBorne(int nouveauQuantiteBorne) throws SQLException {
        variable.updateQuantiteBorne(bdd,nouveauQuantiteBorne);
        System.out.println("la nouvelle quantité des bornes est : "+nouveauQuantiteBorne);
    }

}
