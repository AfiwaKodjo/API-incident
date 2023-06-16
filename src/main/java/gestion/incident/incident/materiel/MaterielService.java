package gestion.incident.incident.materiel;

import gestion.incident.incident.exception.MaterielBadRequestException;
import gestion.incident.incident.exception.MaterielConflictException;
import gestion.incident.incident.exception.MaterielNotFoundException;
import gestion.incident.incident.procedure.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MaterielService {
    @Autowired
    MaterielRepository materielRepository;
    ProcedureRepository procedureRepository;

    public List<Materiel> getAllMateriel(){
        return materielRepository.findAll();
    }

    public String addMateriel(Materiel m) {
        Materiel existingMateriel = materielRepository.findById(m.getIdMateriel()).orElse(null);
        if (existingMateriel == null){
            materielRepository.save(m);
            return "Le matériel a été ajouté avec succès";
        }else
        {
            throw new MaterielConflictException("Le point materiel existe déjà");
        }
    }

    public Materiel get(Long idMateriel) {
        return materielRepository.findById(idMateriel).orElseThrow(
                ()
                        -> new MaterielNotFoundException(
                        "{Un materiel avec l'id " + idMateriel+ " n'existe pas}"));
    }

    public String deleteMaterielUsingId(Long idMateriel){
        if (!materielRepository.existsById(idMateriel)){
            throw new MaterielBadRequestException("{L'id "+idMateriel+" n'existe pas. Revoyez votre saisie.}");
        }
        materielRepository.deleteById(idMateriel);
        return "{Le point materiel "+idMateriel+" a été bien supprimé}";
    }

    public String updateMateriel(Materiel materiel) {
        Materiel existingMateriel = materielRepository.findById(materiel.getIdMateriel()).orElse(null);
        if(existingMateriel == null){
            throw new MaterielNotFoundException("{Ce point materiel n'existe pas}");
        }
        else{
            existingMateriel.setNomMateriel(materiel.getNomMateriel());
            existingMateriel.setQuantiteMateriel(materiel.getQuantiteMateriel());
            materielRepository.save(existingMateriel);
            return "{Votre materiel a été mis à jour}";
        }

    }

    public Materiel getMaterielByNom(String nomMateriel){
        return materielRepository.findByNom(nomMateriel).orElseThrow(
                ()
                        -> new MaterielNotFoundException(
                        "{Un materiel avec le nom " + nomMateriel+ " n'existe pas}"));

    }
}
