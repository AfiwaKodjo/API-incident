package gestion.incident.incident.client;

import com.fasterxml.jackson.annotation.JsonIgnore;
import gestion.incident.incident.agence.Agence;
import gestion.incident.incident.utilisateur.Utilisateur;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;

import java.util.List;
@Entity
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String nomClient;
    private String adresseClient;
    private String contactClient;
    private String emailClient;

    /*@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="fk_client_id", referencedColumnName = "idClient")
    private List <Agence> agence;*/

    @ManyToOne
    @JoinColumn(name = "fk_utilisateur_id")
    private Utilisateur utilisateur;

   public Client() {
    }

    public Client(Long idClient,
                  String nomClient,
                  String adresseClient,
                  String contactClient,
                  String emailClient,
                  Utilisateur utilisateur) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.adresseClient = adresseClient;
        this.contactClient = contactClient;
        this.emailClient = emailClient;
        this.utilisateur =utilisateur;
    }

    public Client(String nomClient,
                  String adresseClient,
                  String contactClient,
                  String emailClient,
                  Utilisateur utilisateur) {
        this.nomClient = nomClient;
        this.adresseClient = adresseClient;
        this.contactClient = contactClient;
        this.emailClient = emailClient;
        this.utilisateur = utilisateur;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getAdresseClient() {
        return adresseClient;
    }

    public void setAdresseClient(String adresseClient) {
        this.adresseClient = adresseClient;
    }

    public String getContactClient() {
        return contactClient;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public void setContactClient(String contactClient) {
        this.contactClient = contactClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    /*public List<Agence> getAgence() {
        return agence;
    }*/

    /*public void setAgence(List<Agence> agence) {
        this.agence = agence;
    }*/

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nomClient='" + nomClient + '\'' +
                ", adresseClient='" + adresseClient + '\'' +
                ", contactClient='" + contactClient + '\'' +
                ", emailClient='" + emailClient + '\'' +
                //", agence=" + agence +
                '}';
    }
}
