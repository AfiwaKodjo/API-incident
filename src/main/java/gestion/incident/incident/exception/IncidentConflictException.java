package gestion.incident.incident.exception;

public class IncidentConflictException extends RuntimeException{
    public IncidentConflictException(){}
    public IncidentConflictException(String message){
        super(message);
    }
}
