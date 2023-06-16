package gestion.incident.incident.procedure;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ProcedureController {
    @Autowired
    ProcedureService procedureService;
    //Afficher toutes les procédures
    @GetMapping("/api/procedures")
    public Collection<Procedure> getAllProcedure(){
        return procedureService.getAllProcedure();
    }

    //Ajouter une procédure dans la base de données
    @PostMapping("/api/procedures/post")
    public String addProcedure(@RequestBody Procedure p){
        return procedureService.addProcedure(p);
    }

    //Afficher une procédure de la base de données
    @GetMapping("/api/procedures/{idProcedure}/get")
    public Procedure getProcedureById(@PathVariable("idProcedure") Long idProcedure ){
        return procedureService.get(idProcedure);

    }

    //Supprimer un élément de la base de données
    @DeleteMapping(value="/api/procedures/{idProcedure}/delete")
    public String deleteProcedure(@PathVariable("idProcedure") Long idProcedure){
        return procedureService.deleteProcedureUsingId(idProcedure);
    }


    //Mettre à jour un élément de la base de données
    @PutMapping(value="/api/procedures/{idProcedure}/put")
    public String updateProcedure(@RequestBody Procedure procedure){
        return procedureService.updateProcedure(procedure);
    }

    //Afficher une procédure par rapport à son nom
    @GetMapping("/api/procedures/")
    public Procedure getProcedureByNom(@PathParam("nomProcedure") String nomProcedure ){
        return procedureService.getProcedureByNom(nomProcedure);

    }

}
