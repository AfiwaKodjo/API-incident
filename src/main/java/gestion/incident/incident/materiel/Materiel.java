package gestion.incident.incident.materiel;


import gestion.incident.incident.client.Client;
import gestion.incident.incident.procedure.Procedure;
import gestion.incident.incident.utilisateur.Utilisateur;
import jakarta.persistence.*;

@Entity
@Table(name = "materiel")
public class Materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriel;
    private String nomMateriel;
    private Integer quantiteMateriel=0;
    @ManyToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "fk_procedure_id")
    private Procedure procedure;

    /*@ManyToOne
    @JoinColumn(name = "fk_utilisateurs_id")
    private Utilisateur utilisateur;*/

    public Materiel() {
    }

    public Materiel(Long idMateriel,
                    String nomMateriel,
                    Integer quantiteMateriel,
                    Procedure procedure,
                    Utilisateur utilisateur) {
        this.idMateriel = idMateriel;
        this.nomMateriel = nomMateriel;
        this.quantiteMateriel = quantiteMateriel;
        this.procedure= procedure;
        //this.utilisateur = utilisateur;
    }

    public Materiel(String nomMateriel,
                    Integer quantiteMateriel,
                    Procedure procedure,
                    Utilisateur utilisateur) {
        this.nomMateriel = nomMateriel;
        this.quantiteMateriel = quantiteMateriel;
        this.procedure= procedure;
        //this.utilisateur=utilisateur;
    }

    public Long getIdMateriel() {
        return idMateriel;
    }

    public void setIdMateriel(Long idMateriel) {
        this.idMateriel = idMateriel;
    }

    public String getNomMateriel() {
        return nomMateriel;
    }

    public void setNomMateriel(String nomMateriel) {
        this.nomMateriel = nomMateriel;
    }

   /* public Utilisateur getUtilisateur() {
        return this.utilisateur;
    }*/

    public Procedure getProcedure() {
        return this.procedure;
    }

    public void setProcedure(Procedure procedure) {
        this.procedure = procedure;
    }

   /* public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }*/

    public Integer getQuantiteMateriel() {
        return quantiteMateriel;
    }

    public void setQuantiteMateriel(Integer quantiteMateriel) {
        this.quantiteMateriel = quantiteMateriel;
    }

    @Override
    public String toString() {
        return "Materiel{" +
                "idMateriel=" + idMateriel +
                ", nomMateriel='" + nomMateriel + '\'' +
                ", quantiteMateriel=" + quantiteMateriel +
                '}';
    }
}
