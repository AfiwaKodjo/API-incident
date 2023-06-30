package gestion.incident.incident.materiel;

import gestion.incident.incident.procedure.ProcedureRepository;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MaterielController {
    @Autowired
    MaterielService materielService;
    ProcedureRepository procedureRepository;

    //Afficher tous les éléments de la base de données
    @GetMapping(value = "/api/materiels/get")
    public List<Materiel> getAllMateriel(){
        return materielService.getAllMateriel();
    }

    //Ajouter un élément dans la base de données
    @PostMapping(value = "/api/materiels/post")
    public String addMateriel(@RequestBody Materiel m){
        return materielService.addMateriel(m);
    }

    //Afficher un matériel de la base de données
    @GetMapping(value = "api/materiels/{idMateriel}/get")
    public Materiel getMaterielById(@PathVariable("idMateriel") Long idMateriel){
        return materielService.get(idMateriel);
    }

    //Supprimer un élément de la base de données
    @DeleteMapping(value="/api/materiels/{idMateriel}/delete")
    public String deleteMateriel(@PathVariable("idMateriel") Long idMateriel){
        return materielService.deleteMaterielUsingId(idMateriel);
    }

    //Mettre à jour un élément de la base de données
    @PutMapping(value="/api/materiels/{idMateriel}/put")
    public String updateMateriel(@RequestBody Materiel materiel,@PathVariable("idMateriel") Long idMateriel){
        return materielService.updateMateriel(materiel);
    }

    //Afficher une procédure par rapport à son nom
    @GetMapping("/api/materiels/")
    public Materiel getMaterielByNom(@PathParam("nomProcedure") String nomProcedure ){
        return materielService.getMaterielByNom(nomProcedure);

    }

}
