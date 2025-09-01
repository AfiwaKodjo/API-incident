package gestion.incident.incident.incidents;

import gestion.incident.incident.agence.AgenceRepository;
import gestion.incident.incident.email.EmailService;
import gestion.incident.incident.enumeration.MesPriorites;
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
    @Autowired
    private EmailService emailService;
    public Collection<Incident> getAllIncident(){
        return incidentRepository.findAll();
    }

    /*public String addIncident(Incident i){
        //Incident existingIncident = incidentRepository.findById(i.getIdIncident()).orElse(null);
        Incident existingIncident = incidentRepository.save(i);
        if (existingIncident != null){
            return "L'incident a été ajouté avec succès";
        }else
        {
            throw new IncidentConflictException("L'incident existe déjà");
        }
    }*/

    public String addIncident(Incident i) {
        Incident existingIncident = incidentRepository.save(i);
        if (existingIncident != null) {
            assignPriorityToIncident(existingIncident.getIdIncident()); // Appel de la méthode assignPriorityToIncident
            return "L'incident a été ajouté avec succès";
        } else {
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


   /* public void assignPriorityToIncident(Long idIncident, MesPriorites newPriority) {
        Incident incident = incidentRepository.findById(idIncident).orElse(null);

        // Récupérer l'adresse e-mail de l'utilisateur associé à l'incident
        String userEmail = incident.getAgence().getClient().getUtilisateur().getEmail();

        if (userEmail != null) {
            String subject = "Nouvelle priorité d'incident";
            String text = "Consultez votre page afin de régler cet incident dont la priorité est : " + newPriority+ "!!";

            emailService.sendEmail(userEmail, subject, text);
        }else{

            System.out.println("Revoyez votre saisie");
        }



}*/

    //Envoi d'email
    public void assignPriorityToIncident(Long idIncident) {
        Incident incident = incidentRepository.findById(idIncident).orElse(null);

        if (incident != null) {
            // Récupérer l'adresse e-mail de l'utilisateur associé à l'incident
            String userEmail = incident.getAgence().getClient().getUtilisateur().getEmail();

            MesPriorites newPriority = incident.getPrioriteIncident();

            if (userEmail != null) {
                String subject = incident.getAgence().getClient().getNomClient()+" - Nouveau incident";
                String text = "Consultez votre page afin de régler l'incident de la société " +incident.getAgence().getClient().getNomClient()+ " pour son agence qui se trouve à "+incident.getAgence().getLieuAgence();

                emailService.sendEmail(userEmail, subject, text);
            } else {
                System.out.println("Revoyez votre saisie");
            }
        } else {
            System.out.println("Incident non trouvé");
        }
    }


}
