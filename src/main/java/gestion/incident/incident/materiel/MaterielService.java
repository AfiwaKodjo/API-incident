package gestion.incident.incident.materiel;

import gestion.incident.incident.enumeration.MesTypes;
import gestion.incident.incident.exception.MaterielBadRequestException;
import gestion.incident.incident.exception.MaterielConflictException;
import gestion.incident.incident.exception.MaterielNotFoundException;
import gestion.incident.incident.procedure.Procedure;
import gestion.incident.incident.procedure.ProcedureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class MaterielService {
    @Autowired
    MaterielRepository materielRepository;
    ProcedureRepository procedureRepository;

    public List<Materiel> getAllMateriel(){
        return materielRepository.findAll();
    }

    /*public String addMateriel(Materiel m) {
        //Materiel existingMateriel = materielRepository.findById(m.getIdMateriel()).orElse(null);
        Materiel existingMateriel = materielRepository.save(m);
        if (existingMateriel != null){
            //materielRepository.save(m);
            return "Le matériel a été ajouté avec succès";
        }else
        {
            throw new MaterielConflictException("Le point materiel existe déjà");
        }
    }*/


    public void addMateriel(MultipartFile file, String numeroSerie, String nomMateriel, MesTypes typeMachine, String identifiMachine,
                            Integer quantiteMateriel) throws IOException {
        Materiel m = new Materiel();
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        if(fileName.contains(".."))
        {
            System.out.println("not a a valid file");
        }
        try {
            m.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        m.setNumeroSerie(numeroSerie);
        m.setNomMateriel(nomMateriel);
        m.setTypeMachine(typeMachine);
        m.setIdentifiMachine(identifiMachine);
        m.setQuantiteMateriel(quantiteMateriel);
        m.setImage(file.getOriginalFilename());
        Materiel mat =  materielRepository.save(m);
       mat.setImage(mat.getIdMateriel() + file.getOriginalFilename());
      Materiel matEnd =  materielRepository.save(mat);

        Path uploadPath = Paths.get("C:\\Users\\falac\\Downloads\\incident\\src\\main\\resources\\Images");

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        //String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = file.getInputStream()) {
            Path filePath = uploadPath.resolve(matEnd.getImage());
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException ioe) {
            throw new IOException("Impossible d'enregistrer le fichier: " + fileName, ioe);
        }
    }







    public Materiel get(Long idMateriel) {
        return materielRepository.findById(idMateriel).orElseThrow(
                ()
                        -> new MaterielNotFoundException(
                        "{Un materiel avec l'id " + idMateriel+ " n'existe pas}"));
    }

    public String deleteMaterielUsingId(Long idMateriel){
        if (!materielRepository.existsById(idMateriel)){
            throw new MaterielBadRequestException("{L'id "+idMateriel+" n'existe pas. Revoyez votre saisie.}");
        }
        materielRepository.deleteById(idMateriel);
        return "{Le point materiel "+idMateriel+" a été bien supprimé}";
    }

    public String updateMateriel(Materiel materiel) {
        Materiel existingMateriel = materielRepository.findById(materiel.getIdMateriel()).orElse(null);
        if(existingMateriel == null){
            throw new MaterielNotFoundException("{Ce point materiel n'existe pas}");
        }
        else{
            existingMateriel.setNomMateriel(materiel.getNomMateriel());
            existingMateriel.setQuantiteMateriel(materiel.getQuantiteMateriel());
            existingMateriel.setNumeroSerie(materiel.getNumeroSerie());
            existingMateriel.setTypeMachine(materiel.getTypeMachine());
            existingMateriel.setIdentifiMachine(materiel.getIdentifiMachine());
            existingMateriel.setImage(materiel.getImage());
           // existingMateriel.setUtilisateur(materiel.getUtilisateur());
            //existingMateriel.setProcedure(materiel.getProcedure());
            materielRepository.save(existingMateriel);
            return "{Votre materiel a été mis à jour}";
        }

    }

    public Materiel getMaterielByNom(String nomMateriel){
        return materielRepository.findByNomMateriel(nomMateriel).orElseThrow(
                ()
                        -> new MaterielNotFoundException(
                        "{Un materiel avec le nom " + nomMateriel+ " n'existe pas}"));

    }

    public Resource loadImage(Long idMateriel) {
        try {
            Optional<Materiel> optional = materielRepository.findById(idMateriel);
            if (optional.isEmpty()) {
                throw new MaterielNotFoundException("Aucune image n'existe avec l'id " + idMateriel);
            }
            Path file = Paths.get("C:\\Users\\falac\\Downloads\\incident\\src\\main\\resources\\Images").resolve(optional.get().getImage());
            System.out.println("*******FILE");
            System.out.println(file);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }


}

