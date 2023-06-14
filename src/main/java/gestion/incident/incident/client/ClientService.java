package gestion.incident.incident.client;

import gestion.incident.incident.exception.ClientBadRequestException;
import gestion.incident.incident.exception.ClientConflictException;
import gestion.incident.incident.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ClientService {
    @Autowired
    ClientRepository clientRepository;
    public Collection<Client> getAllClient(){
        return clientRepository.findAll();
    }

    public String addClient(Client c){
        Client existingClient = clientRepository.findById(c.getIdClient()).orElse(null);
        if (existingClient == null){
            clientRepository.save(c);
            return "Le client a été ajouté avec succès";
        }else{
            throw new ClientConflictException("Le client existe déjà");
        }
    }

    public Client get(Long idClient) {
        return clientRepository.findById(idClient).orElseThrow(
                ()
                        -> new ClientNotFoundException(
                        "{Un client avec l'id " + idClient+ " n'existe pas}"));
    }

    public String updateClient(Client client) {
        Client existingClient = clientRepository.findById(client.getIdClient()).orElse(null);
        if (existingClient == null){
            throw new ClientNotFoundException("{Ce client n'existe pas}");
        }else{
            existingClient.setNomClient(client.getNomClient());
            existingClient.setPrenomClient(client.getPrenomClient());
            existingClient.setAdresseClient(client.getAdresseClient());
            existingClient.setContactClient(client.getContactClient());
            existingClient.setEmailClient(client.getEmailClient());
            clientRepository.save(existingClient);
            return "{Le client a été mis à jour}";
        }
    }

    public String deleteClientUsingId(Long idClient) {
        if (!clientRepository.existsById(idClient)){
            throw new ClientBadRequestException("{L'id "+idClient+" n'existe pas. Revoyez votre saisie.}");
        }
        clientRepository.deleteById(idClient);
        return "{Le client "+idClient+" a été bien supprimé}";
    }


    public Client getClientByNom(String nomClient){
        return clientRepository.findByNom(nomClient).orElseThrow(
                ()
                        -> new ClientNotFoundException(
                        "{Un client avec le nom " + nomClient+ " n'existe pas}"));

    }
}
