package fr.ul.miage.Genie_Logiciel_Projet_2022.model;

public class Compte {
    private int idCompte;
    private String email;
    private String mdp;
    private String nom;
    private String prenom;
    private int age;
    private boolean estClient = false;
    private boolean estGerant = false;
    private boolean estAdministrateur = false;

    public Compte() {}

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

    public void setEstClient(boolean estClient) {
        this.estClient = estClient;
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
}
