package gestion.incident.incident.client;

import gestion.incident.incident.agence.Agence;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
@Entity
@Transactional
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClient;
    private String nomClient;
    private String prenomClient;
    private String adresseClient;
    private String contactClient;
    private String emailClient;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name ="fk_client_id", referencedColumnName = "idClient")
    private List <Agence> agence;

    public Client() {
    }

    public Client(Long idClient, String nomClient,
                  String prenomClient,
                  String adresseClient,
                  String contactClient,
                  String emailClient) {
        this.idClient = idClient;
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.adresseClient = adresseClient;
        this.contactClient = contactClient;
        this.emailClient = emailClient;
    }

    public Client(String nomClient,
                  String prenomClient,
                  String adresseClient,
                  String contactClient,
                  String emailClient) {
        this.nomClient = nomClient;
        this.prenomClient = prenomClient;
        this.adresseClient = adresseClient;
        this.contactClient = contactClient;
        this.emailClient = emailClient;
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

    public String getPrenomClient() {
        return prenomClient;
    }

    public void setPrenomClient(String prenomClient) {
        this.prenomClient = prenomClient;
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

    public void setContactClient(String contactClient) {
        this.contactClient = contactClient;
    }

    public String getEmailClient() {
        return emailClient;
    }

    public void setEmailClient(String emailClient) {
        this.emailClient = emailClient;
    }

    public List<Agence> getAgence() {
        return agence;
    }

    public void setAgence(List<Agence> agence) {
        this.agence = agence;
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", nomClient='" + nomClient + '\'' +
                ", prenomClient='" + prenomClient + '\'' +
                ", adresseClient='" + adresseClient + '\'' +
                ", contactClient='" + contactClient + '\'' +
                ", emailClient='" + emailClient + '\'' +
                ", agence=" + agence +
                '}';
    }
}
