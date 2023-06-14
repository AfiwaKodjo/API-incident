package gestion.incident.incident.exception;

public class ClientBadRequestException extends RuntimeException{
    public ClientBadRequestException(){}

    public ClientBadRequestException(String message){
        super(message);
    }
}
