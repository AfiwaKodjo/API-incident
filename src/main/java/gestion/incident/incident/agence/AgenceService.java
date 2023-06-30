package gestion.incident.incident.agence;

import gestion.incident.incident.exception.AgenceBadRequestException;
import gestion.incident.incident.exception.AgenceConflictException;
import gestion.incident.incident.exception.AgenceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgenceService {
    @Autowired
    AgenceRepository agenceRepository;


    public List<Agence> getAllAgence(){
        return agenceRepository.findAll();
    }

    public String addAgence(Agence a) {
        Agence existingAgence = agenceRepository.findById(a.getIdAgence()).orElse(null);
        if (existingAgence == null){
            agenceRepository.save(a);
            return "L'agence a été ajoutée avec succès";
        }else
        {
            throw new AgenceConflictException("L'agence existe déjà");
        }
    }

    public Agence get(Long idAgence) {
        return agenceRepository.findById(idAgence).orElseThrow(
                ()
                        -> new AgenceNotFoundException(
                        "{Une agence avec l'id " + idAgence+ " n'existe pas}"));
    }

    public String deleteAgenceUsingId(Long idAgence){
        if (!agenceRepository.existsById(idAgence)){
            throw new AgenceBadRequestException("{L'id "+idAgence+" n'existe pas. Revoyez votre saisie.}");
        }
        agenceRepository.deleteById(idAgence);
        return "{L'agence"+idAgence+" a été bien supprimée}";
    }

    public String updateAgence(Agence agence) {
        Agence existingAgence = agenceRepository.findById(agence.getIdAgence()).orElse(null);
        if(existingAgence == null){
            throw new AgenceNotFoundException("{Cette agence n'existe pas}");
        }
        else{
            existingAgence.setLieuAgence(agence.getLieuAgence());
            existingAgence.setTelephoneAgence(agence.getTelephoneAgence());
            agenceRepository.save(existingAgence);
            return "{Votre agence a été mise à jour}";
        }

    }

    public Agence getAgenceByNom(String lieuAgence){
        return agenceRepository.findByLieuAgence(lieuAgence).orElseThrow(
                ()
                        -> new AgenceNotFoundException(
                        "{Une agence avec le nom de lieu" + lieuAgence+ " n'existe pas}"));

    }

}
