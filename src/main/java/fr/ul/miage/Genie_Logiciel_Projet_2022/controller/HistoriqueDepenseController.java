package fr.ul.miage.Genie_Logiciel_Projet_2022.controller;

import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.HistoriqueDepense;

import java.sql.SQLException;

public class HistoriqueDepenseController {
    private DatabaseController bdd;

    public HistoriqueDepenseController(DatabaseController bd) {
        bdd = bd;
    }

    public void getDepenseByClient(int idCompte) throws SQLException {

        HistoriqueDepense historiqueDepense=new HistoriqueDepense();
        historiqueDepense.getDepenseByClient(bdd,idCompte);


    }
}
