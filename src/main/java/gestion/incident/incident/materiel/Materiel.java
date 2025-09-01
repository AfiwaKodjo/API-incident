package gestion.incident.incident.materiel;


import gestion.incident.incident.enumeration.MesTypes;
import gestion.incident.incident.procedure.Procedure;
import gestion.incident.incident.utilisateur.Utilisateur;
import jakarta.mail.Multipart;
import jakarta.persistence.*;

@Entity
@Table(name = "materiel")
public class Materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriel;
    private String NumeroSerie;
    @Column(unique = true) // Ajoutez cette annotation pour la contrainte d'unicité
    private String nomMateriel;
    private MesTypes TypeMachine;
   @Lob
   @Column(columnDefinition = "MEDIUMBLOB")
    private String image;
    private String IdentifiMachine;
    private Integer quantiteMateriel=0;
    @ManyToOne
    @JoinColumn(name = "fk_procedure_id")
    private Procedure procedure;

    /*@ManyToOne
    @JoinColumn(name = "fk_utilisateurs_id")
    private Utilisateur utilisateur;*/

    public Materiel() {
    }
    public Materiel(Long idMateriel,
                    String numeroSerie,
                    String nomMateriel,
                    MesTypes typeMachine,
                    String image,
                    String identifiMachine,
                    Integer quantiteMateriel,
                    Procedure procedure) {
        this.idMateriel = idMateriel;
        NumeroSerie = numeroSerie;
        this.nomMateriel = nomMateriel;
        TypeMachine = typeMachine;
        this.image = image;
        IdentifiMachine = identifiMachine;
        this.quantiteMateriel = quantiteMateriel;
        this.procedure = procedure;
    }

    public Materiel(String numeroSerie,
                    String nomMateriel,
                    MesTypes typeMachine,
                    String image,
                    String identifiMachine,
                    Integer quantiteMateriel,
                    Procedure procedure) {
        NumeroSerie = numeroSerie;
        this.nomMateriel = nomMateriel;
        TypeMachine = typeMachine;
        this.image = image;
        IdentifiMachine = identifiMachine;
        this.quantiteMateriel = quantiteMateriel;
        this.procedure = procedure;
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

    public String getNumeroSerie() {
        return NumeroSerie;
    }

    public MesTypes getTypeMachine() {
        return TypeMachine;
    }

    public String getImage() {
        return image;
    }

    public String getIdentifiMachine() {
        return IdentifiMachine;
    }

    public void setNumeroSerie(String numeroSerie) {
        NumeroSerie = numeroSerie;
    }

    public void setTypeMachine(MesTypes typeMachine) {
        TypeMachine = typeMachine;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setIdentifiMachine(String identifiMachine) {
        IdentifiMachine = identifiMachine;
    }

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
