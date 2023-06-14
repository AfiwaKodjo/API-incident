package gestion.incident.incident.agence;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AgenceController {
    @Autowired
    AgenceService agenceService;

    //Afficher tous les éléments de la base de données
    @GetMapping(value = "/api/agences")
    public List<Agence> getAllAgence(){
        return agenceService.getAllAgence();
    }

    //Ajouter un élément dans la base de données
    @PostMapping(value = "/api/agences/post")
    public String addAgence(@RequestBody Agence a){
        return agenceService.addAgence(a);
    }

    //Afficher une agence de la base de données
    @GetMapping(value = "api/agences/{idAgence}/get")
    public Agence getAgenceById(@PathVariable("idClient") Long idAgence){
        return agenceService.get(idAgence);
    }

    //Supprimer un élément de la base de données
    @DeleteMapping(value="/api/agences/{idAgence}/delete")
    public String deleteAgence(@PathVariable("idAgence") Long idAgence){
        return agenceService.deleteAgenceUsingId(idAgence);
    }

    //Mettre à jour un élément de la base de données
    @PutMapping(value="/api/agences/{idClient}/put")
    public String updateAgence(@RequestBody Agence agence,@PathVariable("idAgence") Long idAgence){
        return agenceService.updateAgence(agence);
    }

    //Afficher une agence par rapport à son nom
    @GetMapping("/api/agences/")
    public Agence getAgenceByNom(@PathParam("nomAgence") String nomAgence ){
        return agenceService.getAgenceByNom(nomAgence);

    }
}
