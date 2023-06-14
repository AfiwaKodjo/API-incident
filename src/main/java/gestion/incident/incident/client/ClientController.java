package gestion.incident.incident.client;

import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ClientController {

    ClientService clientService;
    //Afficher tous les éléments de la base de données
    @GetMapping(value = "/api/clients")
    public Collection<Client> getAllClient(){
        return clientService.getAllClient();
    }

    //Ajouter un élément dans la base de données
    @PostMapping(value ="/api/clients/post" )
    public String addClient(@RequestBody Client c){
        return clientService.addClient(c);
    }

    //Afficher un élément de la base de données

    @GetMapping(value = "/api/clients/{idClient}/get")
    public Client getClientById(@PathVariable("idClient") Long idClient){
        return clientService.get(idClient);
    }

    //Message de bienvenue(essayage)
   @GetMapping(value="/")
    public String getPage(){
        return "Bienvenue sur InMSoft";
    }

    //Supprimer un élément de la base de données
    @DeleteMapping(value="/api/clients/{idClient}/delete")
    public String deleteClient(@PathVariable("idClient") Long idClient){
        return clientService.deleteClientUsingId(idClient);
    }

    //Mettre à jour un élément de la base de données
    @PutMapping(value="/api/clients/{idClient}/put")
    public String updateClient(@RequestBody Client client){
        return clientService.updateClient(client);
    }

    //Afficher un client par rapport à son nom
    @GetMapping(value="/api/clients/")
    public Client getClientByNom(@PathParam("nomClient") String nomClient ){
        return clientService.getClientByNom(nomClient);

    }

}
