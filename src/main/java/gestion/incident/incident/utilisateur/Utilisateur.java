package gestion.incident.incident.utilisateur;

import gestion.incident.incident.client.Client;
import gestion.incident.incident.enumeration.MesRoles;
import gestion.incident.incident.incidents.Incident;
import gestion.incident.incident.materiel.Materiel;
import gestion.incident.incident.procedure.Procedure;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "utilisateur")
public class Utilisateur implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    private String nom;
    private String prenom;
    private String mot_de_passe;
    private String email;
    @Enumerated(EnumType.STRING)
    private MesRoles role;

    @OneToMany
    @JoinColumn(name = "fk_Util_id")
    private List<Incident>incidents;
    @OneToMany
    @JoinColumn(name = "fk_UtilProced_id")
    private List<Procedure>procedures;
    @OneToMany
    @JoinColumn(name = "fk_UtilCli_id")
    private List<Client>clients;
    @OneToMany
    @JoinColumn(name = "fk_UtilMat_id")
    private List<Materiel>materiels;

   /* public Utilisateur() {
    }

    public Utilisateur(Long id,
                       String nom,
                       String prenom,
                       String mot_de_passe,
                       String email,
                       MesRoles role) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "idUtil=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", mot_de_passe='" + mot_de_passe + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                '}';
    }*/

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
       // return List.of(new SimpleGrantedAuthority(role.name()));
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return mot_de_passe;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }



}
