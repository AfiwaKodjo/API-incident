package gestion.incident.incident.agence;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AgenceController {
    @Autowired
    AgenceService agenceService;
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

    //Afficher tous les éléments de la base de données
    @GetMapping(value = "/api/agences/get")
    @PreAuthorize("hasAuthority('admin:read')")
    public List<Agence> getAllAgence(){
        return agenceService.getAllAgence();
    }

    //Ajouter un élément dans la base de données
    @PostMapping(value = "/api/agences/post")
    @PreAuthorize("hasAuthority('admin:create')")
    public String addAgence(@RequestBody Agence a){
        return agenceService.addAgence(a);
    }

    //Afficher une agence de la base de données
    @GetMapping(value = "api/agences/{idAgence}/get")
    @PreAuthorize("hasAuthority('admin:read')")
    public Agence getAgenceById(@PathVariable("idAgence") Long idAgence){
        return agenceService.get(idAgence);
    }

    //Supprimer un élément de la base de données
    @DeleteMapping(value="/api/agences/{idAgence}/delete")
    public String deleteAgence(@PathVariable("idAgence") Long idAgence){
        return agenceService.deleteAgenceUsingId(idAgence);
    }

    //Mettre à jour un élément de la base de données
    @PutMapping(value="/api/agences/{idAgence}/put")
    public String updateAgence(@RequestBody Agence agence,@PathVariable("idAgence") Long idAgence){
        return agenceService.updateAgence(agence);
    }

    //Afficher une agence par rapport à son nom
    @GetMapping("/api/agences/")
    public Agence getAgenceByNom(@PathParam("nomAgence") String nomAgence ){
        return agenceService.getAgenceByNom(nomAgence);

    }
}
