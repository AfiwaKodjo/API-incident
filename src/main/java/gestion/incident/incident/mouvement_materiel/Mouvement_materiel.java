package gestion.incident.incident.mouvement_materiel;

import gestion.incident.incident.enumeration.MesMouvements;
import gestion.incident.incident.incidents.Incident;
import gestion.incident.incident.materiel.Materiel;
import jakarta.persistence.*;

@Entity
@Table(name = "mouvement_materiel")
public class Mouvement_materiel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idMouvement_Materiel;
    private MesMouvements libelleMouvement_Materiel;
    private Integer quantiteMouvement_Materiel;
    @ManyToOne
    @JoinColumn(name = "fk_materiel_id")
    private Materiel materiel;

    @ManyToOne
    @JoinColumn(name = "incident_id")
    private Incident incident;

    public Mouvement_materiel() {
    }

    public Mouvement_materiel(Long idMouvement_Materiel,
                              MesMouvements libelleMouvement_Materiel,
                              Integer quantiteMouvement_Materiel,
                              Materiel materiel) {
        this.idMouvement_Materiel = idMouvement_Materiel;
        this.libelleMouvement_Materiel = libelleMouvement_Materiel;
        this.quantiteMouvement_Materiel = quantiteMouvement_Materiel;
        this.materiel = materiel;
    }

    public Mouvement_materiel(MesMouvements libelleMouvement_Materiel,
                              Integer quantiteMouvement_Materiel,
                              Materiel materiel) {
        this.libelleMouvement_Materiel = libelleMouvement_Materiel;
        this.quantiteMouvement_Materiel = quantiteMouvement_Materiel;
        this.materiel = materiel;
    }

    public Long getIdMouvement_Materiel() {
        return idMouvement_Materiel;
    }

    public void setIdMouvement_Materiel(Long idMouvement_Materiel) {
        this.idMouvement_Materiel = idMouvement_Materiel;
    }

    public MesMouvements getLibelleMouvement_Materiel() {
        return libelleMouvement_Materiel;
    }

    public void setLibelleMouvement_Materiel(MesMouvements libelleMouvement_Materiel) {
        this.libelleMouvement_Materiel = libelleMouvement_Materiel;
    }

    public Integer getQuantiteMouvement_Materiel() {
        return quantiteMouvement_Materiel;
    }

    public void setQuantiteMouvement_Materiel(Integer quantiteMouvement_Materiel) {
        this.quantiteMouvement_Materiel = quantiteMouvement_Materiel;
    }

    public Materiel getMateriel() {
        return materiel;
    }

    public void setMateriel(Materiel materiel) {
        this.materiel = materiel;
    }

    @Override
    public String toString() {
        return "Mouvement_materiel{" +
                "idMouvement_Materiel=" + idMouvement_Materiel +
                ", libelleMouvement_Materiel=" + libelleMouvement_Materiel +
                ", quantiteMouvement_Materiel=" + quantiteMouvement_Materiel +
                ", materiel=" + materiel +
                '}';
    }
}
