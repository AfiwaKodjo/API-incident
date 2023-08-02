package gestion.incident.incident.incidents;

import gestion.incident.incident.enumeration.MesCanaux;
import gestion.incident.incident.enumeration.MesPriorites;
import gestion.incident.incident.enumeration.MesStatuts;
import gestion.incident.incident.agence.Agence;
import gestion.incident.incident.client.Client;
import gestion.incident.incident.procedure.Procedure;
import jakarta.persistence.*;

import java.time.LocalDateTime;
@Entity
@Table(name = "incident")
public class Incident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idIncident;
    private String nomIncident;
    private String descriptionIncident;
    private  LocalDateTime  dateCreationIncident;
    private LocalDateTime dateClotureIncident;
    @Enumerated(EnumType.STRING)
    private MesPriorites prioriteIncident;
    @Enumerated(EnumType.STRING)
    private MesStatuts statutIncident;
    @Enumerated(EnumType.STRING)
    private MesCanaux canalIncident;
    @ManyToOne
    @JoinColumn(name = "fk_agence_id")
    private Agence agence;

   /* @ManyToOne
    @JoinColumn(name = "fk_client_id")
    private Client client;*/

   /* @ManyToOne
    @JoinColumn(name = "fk_utilisateur_id")
    private Utilisateur utilisateur;*/

    @ManyToOne
    @JoinColumn(name = "fk_procedure_id")
    private Procedure procedure;

    public Incident() {
    }


    public Incident(Long idIncident,
                    String nomIncident,
                    String descriptionIncident,
                    LocalDateTime dateCreationIncident,
                    LocalDateTime dateClotureIncident,
                    MesPriorites prioriteIncident,
                    MesStatuts statutIncident,
                    MesCanaux canalIncident,
                    Agence agence,
                    Client client,
                    //Utilisateur utilisateur,
                    Procedure procedure) {
        this.idIncident = idIncident;
        this.nomIncident=nomIncident;
        this.descriptionIncident = descriptionIncident;
        this.dateCreationIncident = dateCreationIncident;
        this.dateClotureIncident = dateClotureIncident;
        this.prioriteIncident = prioriteIncident;
        this.statutIncident = statutIncident;
        this.canalIncident = canalIncident;
        this.agence = agence;
       // this.client=client;
       // this.utilisateur = utilisateur;
        this.procedure=procedure;
    }


    public Incident(String descriptionIncident,
                    String nomIncident,
                    LocalDateTime dateCreationIncident,
                    LocalDateTime dateClotureIncident,
                    MesPriorites prioriteIncident,
                    MesStatuts statutIncident,
                    MesCanaux canalIncident,
                    Agence agence,
                    Client client) {
        this.nomIncident=nomIncident;
        this.descriptionIncident = descriptionIncident;
        this.dateCreationIncident = dateCreationIncident;
        this.dateClotureIncident = dateClotureIncident;
        this.prioriteIncident = prioriteIncident;
        this.statutIncident = statutIncident;
        this.canalIncident = canalIncident;
        this.agence = agence;
        //this.client=client;
    }

    public Long getIdIncident() {
        return idIncident;
    }

    public void setIdIncident(Long idIncident) {
        this.idIncident = idIncident;
    }

    public String getDescriptionIncident() {
        return descriptionIncident;
    }

    public void setDescriptionIncident(String descriptionIncident) {
        this.descriptionIncident = descriptionIncident;
    }

    public LocalDateTime getDateCreationIncident() {
        return dateCreationIncident;
    }

    public void setDateCreationIncident(LocalDateTime dateCreationIncident) {
        this.dateCreationIncident = dateCreationIncident;
    }

    public Incident(Long idIncident, String nomIncident, String descriptionIncident, LocalDateTime dateCreationIncident, LocalDateTime dateClotureIncident, MesPriorites prioriteIncident, MesStatuts statutIncident, MesCanaux canalIncident, Agence agence, Procedure procedure) {
        this.idIncident = idIncident;
        this.nomIncident = nomIncident;
        this.descriptionIncident = descriptionIncident;
        this.dateCreationIncident = dateCreationIncident;
        this.dateClotureIncident = dateClotureIncident;
        this.prioriteIncident = prioriteIncident;
        this.statutIncident = statutIncident;
        this.canalIncident = canalIncident;
        this.agence = agence;
        this.procedure = procedure;
    }

    public String getNomIncident() {
        return nomIncident;
    }

    public void setNomIncident(String nomIncident) {
        this.nomIncident = nomIncident;
    }

    public LocalDateTime getDateClotureIncident() {
        return dateClotureIncident;
    }

    public void setDateClotureIncident(LocalDateTime dateClotureIncident) {
        this.dateClotureIncident = dateClotureIncident;
    }

    public MesPriorites getPrioriteIncident() {
        return prioriteIncident;
    }

    public void setPrioriteIncident(MesPriorites prioriteIncident) {
        this.prioriteIncident = prioriteIncident;
    }

    public MesStatuts getStatutIncident() {
        return statutIncident;
    }

    public void setStatutIncident(MesStatuts statutIncident) {
        this.statutIncident = statutIncident;
    }

    public MesCanaux getCanalIncident() {
        return canalIncident;
    }

    public void setCanalIncident(MesCanaux canalIncident) {
        this.canalIncident = canalIncident;
    }

    public Agence getAgence() {
        return agence;
    }

    public void setAgence(Agence agence) {
        this.agence = agence;
    }

   /* public Client getClient() {
        return client;
    }*/

   /* public void setClient(Client client) {
        this.client = client;
    }*/

    /*public Utilisateur getUtilisateur() {
        return utilisateur;
    }*/

    /*public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }*/

    public Procedure getProcedure() {
        return procedure;
    }

   public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

    @Override
    public String toString() {
        return "Incident{" +
                "idIncident=" + idIncident +
                ", nomIncident='" + nomIncident + '\'' +
                ", descriptionIncident='" + descriptionIncident + '\'' +
                ", dateCreationIncident=" + dateCreationIncident +
                ", dateClotureIncident=" + dateClotureIncident +
                ", prioriteIncident=" + prioriteIncident +
                ", statutIncident=" + statutIncident +
                ", canalIncident=" + canalIncident +
                //", agence=" + agence +
               // ", client=" + client +
                '}';
    }
}
