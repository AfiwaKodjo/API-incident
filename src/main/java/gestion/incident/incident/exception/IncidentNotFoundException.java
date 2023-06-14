package gestion.incident.incident.exception;

public class IncidentNotFoundException extends RuntimeException{
    public IncidentNotFoundException(){}
    public IncidentNotFoundException(String message){
        super(message);
    }
}
