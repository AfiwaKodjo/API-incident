package gestion.incident.incident.materiel;


import gestion.incident.incident.procedure.Procedure;
import jakarta.persistence.*;

@Entity
@Table(name = "materiel")
public class Materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMateriel;
    private String nomMateriel;
    private Integer quantiteMateriel=0;
    @ManyToOne
    @JoinColumn(name = "fk_procedure_id")
    private Procedure procedure;

    public Materiel() {
    }

    public Materiel(Long idMateriel,
                    String nomMateriel,
                    Integer quantiteMateriel) {
        this.idMateriel = idMateriel;
        this.nomMateriel = nomMateriel;
        this.quantiteMateriel = quantiteMateriel;
    }

    public Materiel(String nomMateriel,
                    Integer quantiteMateriel) {
        this.nomMateriel = nomMateriel;
        this.quantiteMateriel = quantiteMateriel;
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
