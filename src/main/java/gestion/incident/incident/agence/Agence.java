package gestion.incident.incident.agence;

import gestion.incident.incident.client.Client;
import gestion.incident.incident.incidents.Incident;
import jakarta.persistence.*;

@Entity
@Table(name = "agence")
public class Agence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAgence;
    private String lieuAgence;
    private String telephoneAgence;

    @ManyToOne
    @JoinColumn(name ="fk_client_id")
    private Client client;

    public Agence() {
    }

    public Agence(Long idAgence,
                  String lieuAgence,
                  String telephoneAgence,
                  Client client
                  ) {
        this.idAgence = idAgence;
        this.lieuAgence = lieuAgence;
        this.telephoneAgence = telephoneAgence;
        this.client = client;
    }

    public Agence(String lieuAgence,
                  String telephoneAgence) {
        this.lieuAgence = lieuAgence;
        this.telephoneAgence = telephoneAgence;
    }

    public Long getIdAgence() {
        return idAgence;
    }

    public void setIdAgence(Long idAgence) {
        this.idAgence = idAgence;
    }

    public String getLieuAgence() {
        return lieuAgence;
    }

    public void setLieuAgence(String lieuAgence) {
        this.lieuAgence = lieuAgence;
    }

    public String getTelephoneAgence() {
        return telephoneAgence;
    }

    public void setTelephoneAgence(String telephoneAgence) {
        this.telephoneAgence = telephoneAgence;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Agence{" +
                "idAgence=" + idAgence +
                ", lieuAgence='" + lieuAgence + '\'' +
                ", telephoneAgence='" + telephoneAgence + '\'' +
                '}';
    }


}
