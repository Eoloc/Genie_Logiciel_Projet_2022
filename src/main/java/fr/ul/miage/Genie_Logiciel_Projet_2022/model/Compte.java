package fr.ul.miage.Genie_Logiciel_Projet_2022.model;

import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class Compte {
    private int idCompte;
    private String email;
    private String mdp;
    private String nom;
    private String prenom;
    private int age;
    private boolean estClient = true;
    private boolean estGerant = false;
    private boolean estAdministrateur = false;

    public Compte() {}

    public Compte(String email, String mdp){
        this.email = email;
        this.mdp = mdp;
    }
    public Compte(int idCompte, String email, String mdp, String nom, String prenom, int age,
                  boolean estClient, boolean estGerant, boolean estAdministrateur) {
        this.idCompte = idCompte;
        this.email = email;
        this.mdp = mdp;
        this.nom = nom;
        this.prenom = prenom;
        this.age = age;
        this.estClient = estClient;
        this.estGerant = estGerant;
        this.estAdministrateur = estAdministrateur;

    }

    public int getIdCompte() {
        return idCompte;
    }

    public void setIdCompte(int idCompte) {
        this.idCompte = idCompte;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public boolean isEstClient() {
        return estClient;
    }


    public boolean isEstGerant() {
        return estGerant;
    }

    public void setEstGerant(boolean estGerant) {
        this.estGerant = estGerant;
    }

    public boolean isEstAdministrateur() {
        return estAdministrateur;
    }

    public void setEstAdministrateur(boolean estAdministrateur) {
        this.estAdministrateur = estAdministrateur;
    }



    @Override
    public String toString() {
        return "Compte{" +
                "idCompte=" + idCompte +
                ", email='" + email + '\'' +
                ", mdp='" + mdp + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", age=" + age +
                ", estClient=" + estClient +
                ", estGerant=" + estGerant +
                ", estAdministrateur=" + estAdministrateur +

                '}';
    }

    Connection con;
    public ArrayList<Compte> getAllClient(DatabaseController bdd) throws SQLException {
        ArrayList<Compte> comptes = new ArrayList<>();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM compte");
        while (rs.next()) {
            Compte compte = new Compte(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4), rs.getString(5),
                    rs.getInt(6), rs.getBoolean(7), rs.getBoolean(8),
                    rs.getBoolean(9));
            comptes.add(compte);
        }
        return comptes;
    }

    public Compte getClientByEmailPassword(DatabaseController bdd, String email, String mdp) throws SQLException {
        Compte compte = null;
        try (PreparedStatement st = bdd.getCon().prepareStatement("SELECT * FROM compte c where c.email='" + email + "' and c.mdp='" + mdp+"'");
        ResultSet rs = st.executeQuery()){
            while (rs.next()){
                compte = new Compte(rs.getInt(1), rs.getString(2),
                        rs.getString(3), rs.getString(4), rs.getString(5),
                        rs.getInt(6), rs.getBoolean(7), rs.getBoolean(8),
                        rs.getBoolean(9));

            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            compte = null;
        }
        return compte;
    }

    public void setEstClient(boolean estClient) {
        this.estClient = estClient;
    }

    public String inscrire(DatabaseController bdd,Compte cpt) throws SQLException {

        String sql = "INSERT INTO compte (email, mdp, nom, prenom, age, estClient, estGerant, estAdministrateur)"
                + "VALUES(?,?,?,?,?,TRUE,FALSE,FALSE)";

        try (
                PreparedStatement pstmt = bdd.getCon().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(2, cpt.getEmail());
            pstmt.setString(3, cpt.getMdp());
            pstmt.setString(4, cpt.getNom());
            pstmt.setString(5, cpt.getPrenom());
            pstmt.setInt(6, cpt.getAge());
            pstmt.setBoolean(7, cpt.isEstClient());
            pstmt.setBoolean(8, cpt.isEstGerant());
            pstmt.setBoolean(9, cpt.isEstAdministrateur());

            pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return "Compte est bien créé";
        }
}




