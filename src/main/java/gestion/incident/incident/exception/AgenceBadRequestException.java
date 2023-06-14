package gestion.incident.incident.exception;

public class AgenceBadRequestException extends RuntimeException{
    public AgenceBadRequestException(){}
    public AgenceBadRequestException(String message){
        super(message);
    }
}
