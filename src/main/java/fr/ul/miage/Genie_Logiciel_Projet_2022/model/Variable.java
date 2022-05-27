package fr.ul.miage.Genie_Logiciel_Projet_2022.model;

import fr.ul.miage.Genie_Logiciel_Projet_2022.controller.DatabaseController;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Variable {

    private double prixDemiHeure;
    private double prixDemiHeureSurtaxe;
    private int quantiteborne;

    public Variable() {
    }

    public Variable(double prixDemiHeure, double prixDemiHeureSurtaxe, int quantiteborne) {
        this.prixDemiHeure = prixDemiHeure;
        this.prixDemiHeureSurtaxe = prixDemiHeureSurtaxe;
        this.quantiteborne = quantiteborne;
    }

    // cette fonction permet à l'administrateur de fixer/modifier un prix de demi heure
    public void updatePrixDemiHeure(DatabaseController bdd,double nouveauPrixDemiHeure) throws SQLException {
        String sql = "UPDATE variable set prixdemiheure= ?";
        try (
                PreparedStatement pstmt = bdd.getCon().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, nouveauPrixDemiHeure);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    // cette fonction permet à l'administrateur de fixer/modifier un prix de demi heure surtaxe
    public void updatePrixDemiHeureSurtaxe(DatabaseController bdd,double nouveauPrixDemiHeureSurtaxe) throws SQLException {
        String sql = "UPDATE variable set prixdemiheuresurtaxe= ?";
        try (
                PreparedStatement pstmt = bdd.getCon().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setDouble(1, nouveauPrixDemiHeureSurtaxe);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    // cette fonction permet à l'administrateur de fixer/modifier le nombre des bornes
    public void updateQuantiteBorne(DatabaseController bdd,int nouveauQuantiteBorne) throws SQLException {
        String sql = "UPDATE variable set quantiteborne= ?";
        try (
                PreparedStatement pstmt = bdd.getCon().prepareStatement(sql,
                        Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, nouveauQuantiteBorne);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }
}
