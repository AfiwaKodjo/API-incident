package gestion.incident.incident.procedure;

import jakarta.persistence.*;

@Entity
@Table(name = "procedure")
public class Procedure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProcedure;
    private String nomProcedure;
    private String descriptionProcedure;
    public Procedure() {
    }

    public Procedure(Long idProcedure,
                     String nomProcedure,
                     String descriptionProcedure) {
        this.idProcedure = idProcedure;
        this.nomProcedure = nomProcedure;
        this.descriptionProcedure = descriptionProcedure;
    }

    public Procedure(String nomProcedure,
                     String descriptionProcedure) {
        this.nomProcedure = nomProcedure;
        this.descriptionProcedure = descriptionProcedure;
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

    public String getDescriptionProcedure() {
        return descriptionProcedure;
    }

    public void setDescriptionProcedure(String descriptionProcedure) {
        this.descriptionProcedure = descriptionProcedure;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "idProcedure=" + idProcedure +
                ", nomProcedure='" + nomProcedure + '\'' +
                ", descriptionProcedure='" + descriptionProcedure + '\'' +
                '}';
    }
}
