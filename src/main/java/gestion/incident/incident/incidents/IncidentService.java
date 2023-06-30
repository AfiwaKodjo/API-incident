package gestion.incident.incident.incidents;

import gestion.incident.incident.agence.AgenceRepository;
import gestion.incident.incident.exception.IncidentBadRequestException;
import gestion.incident.incident.exception.IncidentConflictException;
import gestion.incident.incident.exception.IncidentNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class IncidentService {
    @Autowired
    IncidentRepository incidentRepository;
    public Collection<Incident> getAllIncident(){
        return incidentRepository.findAll();
    }

    public String addIncident(Incident i){
        Incident existingIncident = incidentRepository.findById(i.getIdIncident()).orElse(null);
        if (existingIncident == null){
            incidentRepository.save(i);
            return "L'incident a été ajouté avec succès";
        }else
        {
            throw new IncidentConflictException("L'incident existe déjà");
        }
    }


    public Incident get(Long idIncident) {
        return incidentRepository.findById(idIncident).orElseThrow(
                ()
                        -> new IncidentNotFoundException(
                        "{Un incident avec l'id " + idIncident+ " n'existe pas}"));
    }

    public String updateIncident(Incident incident) {
        Incident existingIncident = incidentRepository.findById(incident.getIdIncident()).orElse(null);
        if (existingIncident == null){
            throw new IncidentNotFoundException("{Cet incident n'existe pas}");
        }
        else{
            existingIncident.setNomIncident(incident.getNomIncident());
            existingIncident.setDescriptionIncident(incident.getDescriptionIncident());
            existingIncident.setCanalIncident(incident.getCanalIncident());
            existingIncident.setPrioriteIncident(incident.getPrioriteIncident());
            existingIncident.setStatutIncident(incident.getStatutIncident());
            existingIncident.setDateCreationIncident(incident.getDateCreationIncident());
            existingIncident.setDateClotureIncident(incident.getDateClotureIncident());
            incidentRepository.save(existingIncident);
            return "{Votre incident a été mis à jour}";

        }
    }

    public String deleteIncidentUsingId(Long idIncident){
        if (!incidentRepository.existsById(idIncident)){
            throw new IncidentBadRequestException("{L'id "+idIncident+" n'existe pas. Revoyez votre saisie.}");
        }
        incidentRepository.deleteById(idIncident);
        return "{L'incident "+idIncident+" a été bien supprimé}";

    }

    public Incident getIncidentByNom(String nomIncident){
        return incidentRepository.findByNomIncident(nomIncident).orElseThrow(
                ()
                        -> new IncidentNotFoundException(
                        "{Un incident avec le nom " + nomIncident+ " n'existe pas}"));

    }


}
