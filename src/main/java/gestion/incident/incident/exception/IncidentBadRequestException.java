package gestion.incident.incident.exception;

public class IncidentBadRequestException extends RuntimeException{
    public IncidentBadRequestException(){}
    public IncidentBadRequestException(String message){
        super(message);
    }
}
