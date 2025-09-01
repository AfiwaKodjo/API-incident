package gestion.incident.incident.procedure;
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

    public Procedure() {
    }

    public Procedure(Long idProcedure,
                     String nomProcedure,
                     String libelleProcedure) {
        this.idProcedure = idProcedure;
        this.nomProcedure = nomProcedure;
        this.libelleProcedure = libelleProcedure;
    }

    public Procedure(String nomProcedure,
                     String libelleProcedure) {
        this.nomProcedure = nomProcedure;
        this.libelleProcedure = libelleProcedure;
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


    @Override
    public String toString() {
        return "Procedure{" +
                "idProcedure=" + idProcedure +
                ", nomProcedure='" + nomProcedure + '\'' +
                ", libelleProcedure='" + libelleProcedure + '\'' +
                '}';
    }
}
