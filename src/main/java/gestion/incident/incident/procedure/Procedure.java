package gestion.incident.incident.procedure;

import gestion.incident.incident.utilisateur.Utilisateur;
import jakarta.persistence.*;

@Entity
@Table(name = "demarche")
public class Procedure{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcedure;
    @Column(name = "nomProcedure")
    private String nomProcedure;
    @Column(name = "libelleProcedure")
    private String libelleProcedure;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fk_proced_util_id")
    private Utilisateur utilisateur;

    public Procedure() {
    }

    public Procedure(Long idProcedure,
                     String nomProcedure,
                     String libelleProcedure,
                     Utilisateur utilisateur) {
        this.idProcedure = idProcedure;
        this.nomProcedure = nomProcedure;
        this.libelleProcedure = libelleProcedure;
        this.utilisateur = utilisateur;
    }

    public Procedure(String nomProcedure,
                     String libelleProcedure,
                     Utilisateur utilisateur) {
        this.nomProcedure = nomProcedure;
        this.libelleProcedure = libelleProcedure;
        this.utilisateur = utilisateur;
    }

    public Long getIdProcedure() {
        return idProcedure;
    }

    public void setIdProcedure(Long idProcedure) {
        this.idProcedure = idProcedure;
    }

    public String getNomProcedure() {
        return nomProcedure;
    }

    public void setNomProcedure(String nomProcedure) {
        this.nomProcedure = nomProcedure;
    }

    public String getLibelleProcedure() {
        return libelleProcedure;
    }

    public void setLibelleProcedure(String libelleProcedure) {
        this.libelleProcedure = libelleProcedure;
    }


    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "idProcedure=" + idProcedure +
                ", nomProcedure='" + nomProcedure + '\'' +
                ", libelleProcedure='" + libelleProcedure + '\'' +
                '}';
    }
}
