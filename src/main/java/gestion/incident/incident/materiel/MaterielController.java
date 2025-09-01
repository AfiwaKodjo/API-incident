package gestion.incident.incident.materiel;

import com.fasterxml.jackson.databind.ObjectMapper;
import gestion.incident.incident.enumeration.MesTypes;
import gestion.incident.incident.procedure.Procedure;
import gestion.incident.incident.procedure.ProcedureRepository;
import gestion.incident.incident.procedure.ProcedureService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin("http://localhost:4200")
public class MaterielController {
    @Autowired
    MaterielService materielService;
    ProcedureRepository procedureRepository;

    @CrossOrigin(origins = "http://localhost:4200", maxAge = 3600, allowCredentials="true")

    //Afficher tous les éléments de la base de données
    @GetMapping(value = "/api/materiels/get")
    public List<Materiel> getAllMateriel(){
        return materielService.getAllMateriel();
    }

    //Ajouter un élément dans la base de données
   /* @PostMapping(value = "/api/materiels/post")
    public String addMateriel(@RequestBody Materiel m){
        return materielService.addMateriel(m);
    }*/


    @PostMapping(value="/api/materiels/post")
    public String addMateriel(@RequestParam("file") MultipartFile file,
                              @RequestParam("numeroSerie") String numeroSerie,
                              @RequestParam("nomMateriel") String nomMateriel,
                              @RequestParam("typeMachine") MesTypes typeMachine,
                              @RequestParam("identifiMachine") String identifiMachine,
                              @RequestParam("quantiteMateriel") Integer quantiteMateriel
                              ) throws IOException {
        materielService.addMateriel(file, numeroSerie, nomMateriel, typeMachine,identifiMachine, quantiteMateriel);
        return "matériel bien ajouté";
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

    @GetMapping("/api/files/Images/{filename}")
    @ResponseBody
    public ResponseEntity<Resource> getImageFile(@PathVariable("filename") Long idMateriel) {
        System.out.println("IMAGE FILE id "+idMateriel+"***********************************");
        Resource file = materielService.loadImage(idMateriel);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
    }

}
