package gestion.incident.incident.utilisateur;

import gestion.incident.incident.exception.*;
import gestion.incident.incident.procedure.Procedure;
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
        Utilisateur existingUtilisateur = utilisateurRepository.findById(u.getIdUtil()).orElse(null);
        if (existingUtilisateur == null){
            utilisateurRepository.save(u);
            return "L'utilisateur a été ajouté avec succès";
        }else
        {
            throw new UtilisateurConflictException("L'utilisateur existe déjà");
        }
    }

    public Utilisateur get(Long idUtil) {
        return utilisateurRepository.findById(idUtil).orElseThrow(
                ()
                        -> new UtilisateurNotFoundException(
                        "{Un utilisateur avec l'id " + idUtil+ " n'existe pas}"));
    }

    public String deleteUtilisateurUsingId(Long idUtil){
        if (!utilisateurRepository.existsById(idUtil)){
            throw new UtilisateurBadRequestException("{L'id "+idUtil+" n'existe pas. Revoyez votre saisie.}");
        }
        utilisateurRepository.deleteById(idUtil);
        return "{L'utilisateur"+idUtil+" a été bien supprimé}";
    }

    public String updateUtilisateur(Utilisateur utilisateur) {
        Utilisateur existingUtilisateur = utilisateurRepository.findById(utilisateur.getIdUtil()).orElse(null);
        if(existingUtilisateur == null){
            throw new UtilisateurNotFoundException("{Cet utilisateur n'existe pas}");
        }
        else{
            existingUtilisateur.setNom(utilisateur.getNom());
            existingUtilisateur.setPrenom(utilisateur.getPrenom());
            existingUtilisateur.setMot_de_passe(utilisateur.getMot_de_passe());
            existingUtilisateur.setEmail(utilisateur.getEmail());
            utilisateurRepository.save(existingUtilisateur);
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
