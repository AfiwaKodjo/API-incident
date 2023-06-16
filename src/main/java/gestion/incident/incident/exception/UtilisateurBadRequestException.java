package gestion.incident.incident.exception;

public class UtilisateurBadRequestException extends RuntimeException{
    public UtilisateurBadRequestException(){}
    public UtilisateurBadRequestException(String message){
        super(message);
    }
}
