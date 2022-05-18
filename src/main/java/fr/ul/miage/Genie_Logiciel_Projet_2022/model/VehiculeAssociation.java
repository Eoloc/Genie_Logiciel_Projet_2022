package fr.ul.miage.Genie_Logiciel_Projet_2022.model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;

public class VehiculeAssociation {
	private String immatriculation;
	private int idCompte;
	private String statusAssociation;
	
	public VehiculeAssociation() {
		
	}

	public VehiculeAssociation(String immatriculation, int idCompte, String statusAssociation) {
		this.immatriculation = immatriculation;
		this.idCompte = idCompte;
		this.statusAssociation = statusAssociation;
	}
	
	@Override
    public String toString() {
        return "VehiculeAssociation{" +
                "immatriculation=" + this.immatriculation +
                ", idCompte='" + this.idCompte + '\'' +
                ", statusAssociation='" + this.statusAssociation + '\'' +
                '}';
    }
	
	 public static ArrayList<VehiculeAssociation> getAllVehiculeAssociation(DatabaseController bdd) throws SQLException {
	        ArrayList<VehiculeAssociation> vehiculeAssociations = new ArrayList<>();
	        Statement st = bdd.getCon().createStatement();
	        ResultSet rs = st.executeQuery("SELECT * FROM vehiculeAssociation");
	        while(rs.next()) {
	        	VehiculeAssociation vehiculeAssociation = new VehiculeAssociation(rs.getString(1), rs.getInt(2),rs.getString(3));
	        	vehiculeAssociations.add(vehiculeAssociation);
	        }
	        return vehiculeAssociations;
	  }
	
	
}
