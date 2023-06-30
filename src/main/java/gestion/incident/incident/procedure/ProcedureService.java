package gestion.incident.incident.procedure;

import gestion.incident.incident.exception.ProcedureBadRequestException;
import gestion.incident.incident.exception.ProcedureConflictException;
import gestion.incident.incident.exception.ProcedureNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ProcedureService {
   @Autowired
    ProcedureRepository procedureRepository;
    public Collection<Procedure> getAllProcedure(){
        return procedureRepository.findAll();
    }

    public String addProcedure(Procedure p){
        Procedure existingProcedure = procedureRepository.findById(p.getIdProcedure()).orElse(null);
        if (existingProcedure == null){
            procedureRepository.save(p);
            return "La procedure a été ajoutée avec succès";
        }else
        {
            throw new ProcedureConflictException("La procedure existe déjà");
        }
    }


    public Procedure get(Long idProcedure) {
        return procedureRepository.findById(idProcedure).orElseThrow(
                ()
                        -> new ProcedureNotFoundException(
                        "{Une procedure avec l'id " + idProcedure+ " n'existe pas}"));
    }

    public String updateProcedure(Procedure procedure) {
        Procedure existingProcedure = procedureRepository.findById(procedure.getIdProcedure()).orElse(null);
        if (existingProcedure == null){
            throw new ProcedureNotFoundException("{Cette procedure n'existe pas}");
        }
        else{
            existingProcedure.setNomProcedure(procedure.getNomProcedure());
            existingProcedure.setLibelleProcedure(procedure.getLibelleProcedure());
            procedureRepository.save(existingProcedure);
            return "{Votre procedure a été mise à jour}";

        }
    }

    public String deleteProcedureUsingId(Long idProcedure){
        if (!procedureRepository.existsById(idProcedure)){
            throw new ProcedureBadRequestException("{L'id "+idProcedure+" n'existe pas. Revoyez votre saisie.}");
        }
        procedureRepository.deleteById(idProcedure);
        return "{La procedure "+idProcedure+" a été bien supprimée}";

    }

    public Procedure getProcedureByNom(String nomProcedure){
        return procedureRepository.findByNomProcedure(nomProcedure).orElseThrow(
                ()
                        -> new ProcedureNotFoundException(
                        "{Une procedure avec le nom " + nomProcedure+ " n'existe pas}"));

    }


}
