package gestion.incident.incident.exception;

public class UtilisateurConflictException extends RuntimeException{
    public UtilisateurConflictException(){}
    public UtilisateurConflictException(String message){
        super(message);
    }
}
