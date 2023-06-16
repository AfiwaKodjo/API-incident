package gestion.incident.incident.utilisateur;

import gestion.incident.incident.enumeration.MesRoles;

public class Utilisateur {
    private Long idUtil;
    private String nom;
    private String prenom;
    private String mot_de_passe;
    private String email;
    private MesRoles role;

    public Utilisateur() {
    }

    public Utilisateur(Long idUtil,
                       String nom,
                       String prenom,
                       String mot_de_passe,
                       String email,
                       MesRoles role) {
        this.idUtil = idUtil;
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.role = role;
    }

    public Utilisateur(String nom,
                       String prenom,
                       String mot_de_passe,
                       String email,
                       MesRoles role) {
        this.nom = nom;
        this.prenom = prenom;
        this.mot_de_passe = mot_de_passe;
        this.email = email;
        this.role = role;
    }

    public Long getIdUtil() {
        return idUtil;
    }

    public void setIdUtil(Long idUtil) {
        this.idUtil = idUtil;
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

    public String getMot_de_passe() {
        return mot_de_passe;
    }

    public void setMot_de_passe(String mot_de_passe) {
        this.mot_de_passe = mot_de_passe;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public MesRoles getRole() {
        return role;
    }

    public void setRole(MesRoles role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "Utilisateur{" +
                "idUtil=" + idUtil +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }
}
