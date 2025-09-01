package gestion.incident.incident.mouvement_materiel;

import gestion.incident.incident.enumeration.MesMouvements;
import gestion.incident.incident.exception.MaterielNotFoundException;
import gestion.incident.incident.exception.Mouvement_materielBadRequestException;
import gestion.incident.incident.exception.Mouvement_materielConflictException;
import gestion.incident.incident.exception.Mouvement_materielNotFoundException;
import gestion.incident.incident.incidents.IncidentRepository;
import gestion.incident.incident.materiel.Materiel;
import gestion.incident.incident.materiel.MaterielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Mouvement_materielService {
    @Autowired
    Mouvement_materielRepository mouvement_materielRepository;

    @Autowired
    private MaterielRepository materielRepository;
    IncidentRepository incidentRepository;

    public List<Mouvement_materiel> getAllMouvement_materiel(){
        return mouvement_materielRepository.findAll();
    }

   /* public String addMouvement_materiel(Mouvement_materiel o) {
        //Mouvement_materiel existingMouvement_materiel = mouvement_materielRepository.findById(o.getIdMouvement_Materiel()).orElse(null);
        Mouvement_materiel existingMouvement_materiel = mouvement_materielRepository.save(o);
        if (existingMouvement_materiel != null){
            //mouvement_materielRepository.save(o);
            return "Le mouvement du materiel a été ajouté avec succès";
        }else
        {
            throw new Mouvement_materielConflictException("Le mouvement du matériel existe déjà");
        }
    }*/  //ajout qui marche


    /*public String addMouvement_materiel(Mouvement_materiel mouvement) {
        if (mouvement.getLibelleMouvement_Materiel() == MesMouvements.ENTREE) {
            Materiel materiel = mouvement.getMateriel();
            int quantiteMouvement = mouvement.getQuantiteMouvement_Materiel();
            int nouvelleQuantite = materiel.getQuantiteMateriel() + quantiteMouvement;
            materiel.setQuantiteMateriel(nouvelleQuantite);
            materielRepository.save(materiel);
        }

        Mouvement_materiel existingMouvement = mouvement_materielRepository.save(mouvement);
        if (existingMouvement != null) {
            return "Le mouvement du materiel a été ajouté avec succès";
        } else {
            throw new Mouvement_materielConflictException("Le mouvement du matériel existe déjà");
        }
    }*/



    /*public String addMouvement_materiel(Mouvement_materiel o) {
        Integer quantiteMouvement = o.getQuantiteMouvement_Materiel();
        if (quantiteMouvement != null && quantiteMouvement > 0) {
            Materiel materiel = o.getMateriel();
            if (o.getLibelleMouvement_Materiel() == MesMouvements.ENTREE) {
                materiel.setQuantiteMateriel(materiel.getQuantiteMateriel() + quantiteMouvement);
            } else if (o.getLibelleMouvement_Materiel() == MesMouvements.SORTIE) {
                int nouvelleQuantite = materiel.getQuantiteMateriel() - quantiteMouvement;
                if (nouvelleQuantite >= 0) {
                    materiel.setQuantiteMateriel(nouvelleQuantite);
                } else {
                    throw new Mouvement_materielConflictException("La quantité de sortie excède la quantité disponible.");
                }
            }
            materielRepository.save(materiel);
        }

        Mouvement_materiel existingMouvement_materiel = mouvement_materielRepository.save(o);
        if (existingMouvement_materiel != null) {
            // Supprimer la référence au matériel de l'objet Mouvement_materiel pour éviter la redondance
            existingMouvement_materiel.setMateriel(null);
            return "Le mouvement du materiel a été ajouté avec succès";
        } else {
            throw new Mouvement_materielConflictException("Le mouvement du matériel existe déjà");
        }
    }*/


    public String addMouvement_materiel(Mouvement_materiel o) {
        Integer quantiteMouvement = o.getQuantiteMouvement_Materiel();
        if (quantiteMouvement != null && quantiteMouvement > 0) {
            Materiel materiel = o.getMateriel();
            int quantiteMaterielDisponible = materiel.getQuantiteMateriel();  //nouveau
            if (o.getLibelleMouvement_Materiel() == MesMouvements.ENTREE) {
                materiel.setQuantiteMateriel(materiel.getQuantiteMateriel() + quantiteMouvement);
            } else if (o.getLibelleMouvement_Materiel() == MesMouvements.SORTIE) {
                if (quantiteMouvement > quantiteMaterielDisponible) {
                    throw new Mouvement_materielConflictException("La quantité de sortie excède la quantité disponible.");
                }  //nouveau

                // Enregistrez la demande de sortie avec le statut "en attente de validation"
                o.setStatut("En attente de validation");
                //materielRepository.save(materiel);
            }
            materielRepository.save(materiel);
        }

        Mouvement_materiel existingMouvement_materiel = mouvement_materielRepository.save(o);
        if (existingMouvement_materiel != null) {
            // Supprimer la référence au matériel de l'objet Mouvement_materiel pour éviter la redondance
            existingMouvement_materiel.setMateriel(null);
            return "Le mouvement du materiel a été ajouté avec succès";
        } else {
            throw new Mouvement_materielConflictException("Le mouvement du matériel existe déjà");
        }
    }

    public String validerSortieMateriel(Long idMouvement) {
        Optional<Mouvement_materiel> mouvementOptional = mouvement_materielRepository.findById(idMouvement);
        if (mouvementOptional.isPresent()) {
            Mouvement_materiel mouvement = mouvementOptional.get();
            if ("En attente de validation".equals(mouvement.getStatut())) {
                Materiel materiel = mouvement.getMateriel();
                int nouvelleQuantite = materiel.getQuantiteMateriel() - mouvement.getQuantiteMouvement_Materiel();
                if (nouvelleQuantite >= 0) {
                    materiel.setQuantiteMateriel(nouvelleQuantite);
                    materielRepository.save(materiel);
                    mouvement.setStatut("Validé");
                    mouvement_materielRepository.save(mouvement);
                    return "La demande de sortie a été validée et la quantité a été mise à jour.";
                } else {
                    throw new Mouvement_materielConflictException("La quantité de sortie excède la quantité disponible.");
                }
            } else {
                throw new Mouvement_materielConflictException("Le mouvement n'est pas en attente de validation.");
            }
        } else {
            throw new Mouvement_materielNotFoundException("Le mouvement n'a pas été trouvé.");
        }
    }







    public Mouvement_materiel get(Long idMouvement_Materiel) {
        return mouvement_materielRepository.findById(idMouvement_Materiel).orElseThrow(
                ()
                        -> new Mouvement_materielNotFoundException(
                        "{Un mouvement du materiel avec l'id " + idMouvement_Materiel+ " n'existe pas}"));
    }


    public String deleteMouvement_MaterielUsingId(Long idMouvement_Materiel){
        if (!mouvement_materielRepository.existsById(idMouvement_Materiel)){
            throw new Mouvement_materielBadRequestException("{L'id "+idMouvement_Materiel+" n'existe pas. Revoyez votre saisie.}");
        }
        mouvement_materielRepository.deleteById(idMouvement_Materiel);
        return "{Le mouvement de materiel "+idMouvement_Materiel+" a été bien supprimé}";
    }

    public String updateMouvement_materiel(Mouvement_materiel mouvement_materiel) {
        Mouvement_materiel existingMouvement_materiel = mouvement_materielRepository.findById(mouvement_materiel.getIdMouvement_Materiel()).orElse(null);
        if(existingMouvement_materiel == null){
            throw new Mouvement_materielNotFoundException("{Ce mouvement de materiel n'existe pas}");
        }
        else{
            existingMouvement_materiel.setLibelleMouvement_Materiel(mouvement_materiel.getLibelleMouvement_Materiel());
            existingMouvement_materiel.setQuantiteMouvement_Materiel(mouvement_materiel.getQuantiteMouvement_Materiel());
            //existingMouvement_materiel.getMateriel().setNomMateriel(mouvement_materiel.getMateriel().getNomMateriel());
            mouvement_materielRepository.save(existingMouvement_materiel);
            return "{Votre mouvement de materiel a été mis à jour}";
        }

    }

   /* public Mouvement_materiel getMouvement_materielByNom(String libelleMouvement_materiel){
        return mouvement_materielRepository.findByLibelleMouvement_materiel(libelleMouvement_materiel).orElseThrow(
                ()
                        -> new Mouvement_materielNotFoundException(
                        "{Un mouvement de materiel avec le libelle " + libelleMouvement_materiel+ " n'existe pas}"));

    }*/

}
