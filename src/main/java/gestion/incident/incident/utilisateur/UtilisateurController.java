package gestion.incident.incident.utilisateur;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@PreAuthorize("hasRole('ADMIN')")
public class UtilisateurController {
    @Autowired
    UtilisateurService utilisateurService;

    //Afficher tous les éléments de la base de données
    @GetMapping(value = "/api/utilisateurs/get")
    @PreAuthorize("hasAuthority('admin:read')")
   public List<Utilisateur> getAllUtilisateur(){
        return utilisateurService.getAllUtilisateur();
    }

    //Ajouter un élément dans la base de données
    @PostMapping(value = "/api/utilisateurs/post")
    @PreAuthorize("hasAuthority('admin:create')")
    public String addUtilisateur(@RequestBody Utilisateur u){
        return utilisateurService.addUtilisateur(u);
    }

    //Afficher un utilisateur de la base de données
    @GetMapping(value = "api/utilisateurs/{id}/get")
    @PreAuthorize("hasAuthority('admin:read')")
    public Utilisateur getUtilisateurById(@PathVariable("id") Long id){
        return utilisateurService.get(id);
    }

    //Supprimer un élément de la base de données
    @DeleteMapping(value="/api/utilisateurs/{id}/delete")
    @PreAuthorize("hasAuthority('admin:delete')")
    public String deleteUtilisateur(@PathVariable("id") Long id){
        return utilisateurService.deleteUtilisateurUsingId(id);
    }

    //Mettre à jour un élément de la base de données
    @PutMapping(value="/api/utilisateurs/{id}/put")
    @PreAuthorize("hasAuthority('admin:update')")
    public String updateUtilisateur(@RequestBody Utilisateur user, @PathVariable("id") Long id){
        return utilisateurService.updateUtilisateur(user);
    }

    //Afficher un utilisateur par rapport à son nom
    @GetMapping("/api/utilisateurs/")
    @PreAuthorize("hasAuthority('admin:read')")
    public Utilisateur getUtilisateurByNom(@PathParam("nomUtiisateur") String nom ){
        return utilisateurService.getUtilisateurByNom(nom);

    }


}
