package fr.ul.miage.Genie_Logiciel_Projet_2022.model;


import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class VehiculeAssociation {

    private String immatriculation;
    private int idCompte;
    private String statusAssociation;

    public VehiculeAssociation() {
    }

    public VehiculeAssociation(String immatriculation, int idCompte) {
        this.immatriculation = immatriculation;
        this.idCompte = idCompte;
    }

    public VehiculeAssociation(String immatriculation, int idCompte, String statusAssociation) {
        this.immatriculation = immatriculation;
        this.idCompte = idCompte;
        this.statusAssociation = statusAssociation;
    }

    public String getImmatriculation() {
        return immatriculation;
    }

    public void setImmatriculation(String immatriculation) {
        this.immatriculation = immatriculation;
    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public String getStatusAssociation() {
        return statusAssociation;
    }

    public void setStatusAssociation(String statusAssociation) {
        this.statusAssociation = statusAssociation;
    }

    @Override
    public String toString() {
        return "VehiculeAssociation{" +
                "immatriculation='" + immatriculation + '\'' +
                ", idCompte=" + idCompte +
                ", statusAssociation='" + statusAssociation + '\'' +
                '}';
    }
    public void enregistrerPlaque(DatabaseController bdd, String immatriculation, int idCompte) throws SQLException {
        Compte compte = new Compte();
        //idCompte = compte.getIdCompte();
        String statusAssociation="Permanent";
        VehiculeAssociation vehiculeAssociation = null;
        ArrayList<VehiculeAssociation> vehiculeAssociationListPerm = new ArrayList<VehiculeAssociation>();

        try(PreparedStatement st = bdd.getCon().prepareStatement("SELECT * FROM VehiculeAssociation v where v.immatriculation='"+immatriculation+"' and v.statusAssociation='"+statusAssociation+"'");
            ResultSet rs = st.executeQuery()){
            while(rs.next()){
                vehiculeAssociation = new VehiculeAssociation(rs.getString(1),
                        rs.getInt(2),rs.getString(3));
                vehiculeAssociationListPerm.add(vehiculeAssociation);
            }
            if(vehiculeAssociationListPerm.size()>=1){
                statusAssociation="Temporaire";
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }

        String sql = "INSERT INTO vehiculeAssociation (immatriculation,idCompte,statusassociation)"
                + "VALUES(?,?,?)";
        try (
                PreparedStatement pstmt = bdd.getCon().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, immatriculation);
            pstmt.setInt(2, idCompte);
            pstmt.setString(3, statusAssociation);
            pstmt.executeQuery();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }
    public void supprimerPlaque(DatabaseController bdd, String immatriculation){
        String sql = "DELETE FROM vehiculeAssociation "
                + "WHERE immatriculation =?";
        try (
                PreparedStatement pstmt = bdd.getCon().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, immatriculation);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static ArrayList<VehiculeAssociation> getAssociationByImmatriculation(DatabaseController bdd, String immatriculation) throws SQLException {
        ArrayList<VehiculeAssociation> associations = new ArrayList<>();
        Statement st = bdd.getCon().createStatement(); // Si on commence avant une réservation et qu'on finit pendant
        ResultSet rs = st.executeQuery("select * from vehiculeassociation where immatriculation='" + immatriculation + "'");
        while(rs.next()) {
            VehiculeAssociation association = new VehiculeAssociation(rs.getString(1), rs.getInt(2),rs.getString(3));
            associations.add(association);
        }
        return associations;
    }

    public static VehiculeAssociation insertNewAssociation(DatabaseController bdd, String immatriculation, int idCompte, String etatAssociation) throws SQLException {
        String sql = "INSERT INTO reservation (immatriculation, idcompte, etatassociation)"
                + "VALUES(?, ?, ?)";
        try {
            PreparedStatement pstmt = bdd.getCon().prepareStatement(sql);
            pstmt.setString(1, immatriculation);
            pstmt.setInt(2, idCompte);
            pstmt.setString(3, etatAssociation);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
        return getAssociationByImmatriculationAndIdCompte(bdd, immatriculation, idCompte);
    }

    public static VehiculeAssociation getAssociationByImmatriculationAndIdCompte(DatabaseController bdd, String immatriculation, int idCompte) throws SQLException {
        VehiculeAssociation association = null;
        Statement st = bdd.getCon().createStatement(); // Si on commence avant une réservation et qu'on finit pendant
        ResultSet rs = st.executeQuery("select * from vehiculeassociation where immatriculation='" + immatriculation + "' and idcompte='" + idCompte + "'");
        while(rs.next()) {
            association = new VehiculeAssociation(rs.getString(1), rs.getInt(2),rs.getString(3));
        }
        return association;
    }

}
