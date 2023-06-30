package gestion.incident.incident.utilisateur;
import gestion.incident.incident.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UtilisateurService {
    @Autowired
    UtilisateurRepository utilisateurRepository;


    public List<Utilisateur> getAllUtilisateur(){
        return utilisateurRepository.findAll();
    }

    public String addUtilisateur(Utilisateur u) {
        Utilisateur existingUser = utilisateurRepository.findById(u.getId()).orElse(null);
        if (existingUser == null){
            utilisateurRepository.save(u);
            return "L'utilisateur a été ajouté avec succès";
        }else
        {
            throw new UtilisateurConflictException("L'utilisateur existe déjà");
        }
    }

    public Utilisateur get(Long id) {
        return utilisateurRepository.findById(id).orElseThrow(
                ()
                        -> new UtilisateurNotFoundException(
                        "{Un utilisateur avec l'id " + id+ " n'existe pas}"));
    }

    public String deleteUtilisateurUsingId(Long id){
        if (!utilisateurRepository.existsById(id)){
            throw new UtilisateurBadRequestException("{L'id "+id+" n'existe pas. Revoyez votre saisie.}");
        }
        utilisateurRepository.deleteById(id);
        return "{L'utilisateur "+id+" a été bien supprimé}";
    }

    public String updateUtilisateur(Utilisateur user) {
        Utilisateur existingUser = utilisateurRepository.findById(user.getId()).orElse(null);
        if(existingUser == null){
            throw new UtilisateurNotFoundException("{Cet utilisateur n'existe pas}");
        }
        else{
            existingUser.setNom(user.getNom());
            existingUser.setPrenom(user.getPrenom());
            existingUser.setMot_de_passe(user.getMot_de_passe());
            existingUser.setEmail(user.getEmail());
            utilisateurRepository.save(existingUser);
            return "{Votre utilisateur a été mis à jour}";
        }

    }

    public Utilisateur getUtilisateurByNom(String nom){
        return utilisateurRepository.findByNom(nom).orElseThrow(
                ()
                        -> new UtilisateurNotFoundException(
                        "{Un utilisateur avec le nom " +nom+ " n'existe pas}"));

    }

}
