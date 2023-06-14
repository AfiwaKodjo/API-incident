package gestion.incident.incident.mouvement_materiel;

public class Mouvement_materiel {
    private Long idMouvement_Materiel;
    private String libelleMouvement_Materiel;
    private Integer quantiteMouvement_Materiel;

    public Mouvement_materiel() {
    }

    public Mouvement_materiel(Long idMouvement_Materiel,
                              String libelleMouvement_Materiel,
                              Integer quantiteMouvement_Materiel) {
        this.idMouvement_Materiel = idMouvement_Materiel;
        this.libelleMouvement_Materiel = libelleMouvement_Materiel;
        this.quantiteMouvement_Materiel = quantiteMouvement_Materiel;
    }

    public Mouvement_materiel(String libelleMouvement_Materiel,
                              Integer quantiteMouvement_Materiel) {
        this.libelleMouvement_Materiel = libelleMouvement_Materiel;
        this.quantiteMouvement_Materiel = quantiteMouvement_Materiel;
    }

    public Long getIdMouvement_Materiel() {
        return idMouvement_Materiel;
    }

    public void setIdMouvement_Materiel(Long idMouvement_Materiel) {
        this.idMouvement_Materiel = idMouvement_Materiel;
    }

    public String getLibelleMouvement_Materiel() {
        return libelleMouvement_Materiel;
    }

    public void setLibelleMouvement_Materiel(String libelleMouvement_Materiel) {
        this.libelleMouvement_Materiel = libelleMouvement_Materiel;
    }

    public Integer getQuantiteMouvement_Materiel() {
        return quantiteMouvement_Materiel;
    }

    public void setQuantiteMouvement_Materiel(Integer quantiteMouvement_Materiel) {
        this.quantiteMouvement_Materiel = quantiteMouvement_Materiel;
    }
}
