package gestion.incident.incident.exception;

public class UtilisateurNotFoundException extends RuntimeException{
    public UtilisateurNotFoundException(){}
    public UtilisateurNotFoundException(String message){
        super(message);
    }
}
