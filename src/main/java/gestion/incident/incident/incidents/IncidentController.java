package gestion.incident.incident.incidents;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class IncidentController {
   @Autowired
    IncidentService incidentService;

//Afficher tous les incidents
    @GetMapping("/api/incidents")
    public Collection<Incident> getAllIncident(){
        return incidentService.getAllIncident();
    }

    //Ajouter un incident dans la base de données
    @PostMapping("/api/incidents/post")
    public String addIncident(@RequestBody Incident i){
        return incidentService.addIncident(i);
    }

    //Afficher un incident de la base de données
    @GetMapping("/api/incidents/{id}/get")
    public Incident getIncidentById(@PathVariable("idIncident") Long idIncident ){
        return incidentService.get(idIncident);

    }

    //Supprimer un élément de la base de données
    @DeleteMapping(value="/api/incidents/{id}/delete")
    public String deleteIncident(@PathVariable("idIncident") Long idIncident){
        return incidentService.deleteIncidentUsingId(idIncident);
    }


    //Mettre à jour un élément de la base de données
    @PutMapping(value="/api/incidents/{id}/put")
    public String updateIncident(@RequestBody Incident incident){
        return incidentService.updateIncident(incident);
    }

    //Afficher un incident par rapport à son nom
    @GetMapping("/api/incidents/")
    public Incident getIncidentByNom(@PathParam("nomIncident") String nomIncident ){
        return incidentService.getIncidentByNom(nomIncident);

    }

}
