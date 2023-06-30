package gestion.incident.incident.mouvement_materiel;

import gestion.incident.incident.exception.Mouvement_materielBadRequestException;
import gestion.incident.incident.exception.Mouvement_materielConflictException;
import gestion.incident.incident.exception.Mouvement_materielNotFoundException;
import gestion.incident.incident.incidents.IncidentRepository;
import gestion.incident.incident.materiel.MaterielRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Mouvement_materielService {
    @Autowired
    Mouvement_materielRepository mouvement_materielRepository;
    MaterielRepository materielRepository;
    IncidentRepository incidentRepository;

    public List<Mouvement_materiel> getAllMouvement_materiel(){
        return mouvement_materielRepository.findAll();
    }

    public String addMouvement_materiel(Mouvement_materiel o) {
        Mouvement_materiel existingMouvement_materiel = mouvement_materielRepository.findById(o.getIdMouvement_Materiel()).orElse(null);
        if (existingMouvement_materiel == null){
            mouvement_materielRepository.save(o);
            return "Le mouvement du materiel a été ajouté avec succès";
        }else
        {
            throw new Mouvement_materielConflictException("Le mouvement du matériel existe déjà");
        }
    }

    public Mouvement_materiel get(Long idMouvement_materiel) {
        return mouvement_materielRepository.findById(idMouvement_materiel).orElseThrow(
                ()
                        -> new Mouvement_materielNotFoundException(
                        "{Un mouvement du materiel avec l'id " + idMouvement_materiel+ " n'existe pas}"));
    }

    public String deleteMouvement_materielUsingId(Long idMouvement_materiel){
        if (!mouvement_materielRepository.existsById(idMouvement_materiel)){
            throw new Mouvement_materielBadRequestException("{L'id "+idMouvement_materiel+" n'existe pas. Revoyez votre saisie.}");
        }
        mouvement_materielRepository.deleteById(idMouvement_materiel);
        return "{Le mouvement de materiel"+idMouvement_materiel+" a été bien supprimé}";
    }

    public String updateMouvement_materiel(Mouvement_materiel mouvement_materiel) {
        Mouvement_materiel existingMouvement_materiel = mouvement_materielRepository.findById(mouvement_materiel.getIdMouvement_Materiel()).orElse(null);
        if(existingMouvement_materiel == null){
            throw new Mouvement_materielNotFoundException("{Ce mouvement de materiel n'existe pas}");
        }
        else{
            existingMouvement_materiel.setLibelleMouvement_Materiel(mouvement_materiel.getLibelleMouvement_Materiel());
            existingMouvement_materiel.setQuantiteMouvement_Materiel(mouvement_materiel.getQuantiteMouvement_Materiel());
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
