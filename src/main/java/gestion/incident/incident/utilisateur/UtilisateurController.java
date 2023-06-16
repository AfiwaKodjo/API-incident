package gestion.incident.incident.utilisateur;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    //Afficher tous les éléments de la base de données
    @GetMapping(value = "/api/utilisateurs")
    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurService.getAllUtilisateur();
    }

    //Ajouter un élément dans la base de données
    @PostMapping(value = "/api/utilisateurs/post")
    public String addUtilisateur(@RequestBody Utilisateur u){
        return utilisateurService.addUtilisateur(u);
    }

    //Afficher un utilisateur de la base de données
    @GetMapping(value = "api/utilisateurs/{idUtil}/get")
    public Utilisateur getUtilisateurById(@PathVariable("idUtil") Long idUtil){
        return utilisateurService.get(idUtil);
    }

    //Supprimer un élément de la base de données
    @DeleteMapping(value="/api/utilisateurs/{idUtil}/delete")
    public String deleteUtilisateur(@PathVariable("idUtil") Long idUtil){
        return utilisateurService.deleteUtilisateurUsingId(idUtil);
    }

    //Mettre à jour un élément de la base de données
    @PutMapping(value="/api/utilisateurs/{idUtil}/put")
    public String updateUtilisateur(@RequestBody Utilisateur utilisateur,@PathVariable("idUtil") Long idUtil){
        return utilisateurService.updateUtilisateur(utilisateur);
    }

    //Afficher un utilisateur par rapport à son nom
    @GetMapping("/api/utilisateurs/")
    public Utilisateur getUtilisateurByNom(@PathParam("nomUtiisateur") String nom ){
        return utilisateurService.getUtilisateurByNom(nom);

    }
}
