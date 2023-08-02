package gestion.incident.incident.mouvement_materiel;

import gestion.incident.incident.exception.MaterielNotFoundException;
import gestion.incident.incident.incidents.IncidentService;
import gestion.incident.incident.materiel.Materiel;
import gestion.incident.incident.materiel.MaterielService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class Mouvement_materielController {
   @Autowired
    Mouvement_materielService mouvement_materielService;
   MaterielService materielService;
   IncidentService incidentService;
    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

    //Afficher tous les éléments de la base de données
    @GetMapping(value = "/api/mouvements/get")
    public List<Mouvement_materiel> getAllMouvement_materiel(){
        return mouvement_materielService.getAllMouvement_materiel();
    }

    //Ajouter un élément dans la base de données
    @PostMapping(value = "/api/mouvements/post")
    public String addMouvement_materiel(@RequestBody Mouvement_materiel o){
        return mouvement_materielService.addMouvement_materiel(o);
    }

    //Afficher un mouvement de la base de données
    @GetMapping(value = "api/mouvements/{idMouvement_Materiel}/get")
    public Mouvement_materiel getMouvement_MaterielById(@PathVariable("idMouvement_Materiel") Long idMouvement_Materiel){
        return mouvement_materielService.get(idMouvement_Materiel);
    }


    //Supprimer un élément de la base de données
    @DeleteMapping(value="/api/mouvements/{idMouvement_Materiel}/delete")
    public String deleteMouvement_Materiel(@PathVariable("idMouvement_Materiel") Long idMouvement_Materiel){
        return mouvement_materielService.deleteMouvement_MaterielUsingId(idMouvement_Materiel);
    }

    //Mettre à jour un élément de la base de données
    @PutMapping(value="/api/mouvements/{idMouvement_Materiel}/put")
    public String updateMouvement_materiel(@RequestBody Mouvement_materiel mouvement_materiel,@PathVariable("idMouvement_materiel") Long idMouvement_materiel){
        return mouvement_materielService.updateMouvement_materiel(mouvement_materiel);
    }

    //Afficher un mouvement par rapport à son nom
    /*@GetMapping("/api/mouvements/")
    public Mouvement_materiel getMouvement_materielByNom(@PathParam("libelleMouvement_materiel") String libelleMouvement_materiel ){
        return mouvement_materielService.getLibelleMouvement_materielByLibelle(libelleMouvement_materiel);

    }*/




}
