import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;
import fr.ul.miage.Genie_Logiciel_Projet_2022.model.Compte;

import java.sql.SQLException;
import java.util.ArrayList;


public class Launcher {
    public static void main(String[] args) throws SQLException {
        DatabaseController bdd = new DatabaseController("postgres", "alex57");
        bdd.connexionDatabase();
        ArrayList<Compte> comptes = bdd.getAllClient();
        for(Compte compte : comptes){
            System.out.println(compte);
        }
    }



}
