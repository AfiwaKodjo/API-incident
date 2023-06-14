package gestion.incident.incident.exception;

public class AgenceNotFoundException extends RuntimeException{
    public AgenceNotFoundException(){}
    public AgenceNotFoundException(String message){
        super(message);
    }
}
