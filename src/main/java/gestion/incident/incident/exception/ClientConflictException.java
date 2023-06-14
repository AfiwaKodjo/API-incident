package gestion.incident.incident.exception;

public class ClientConflictException extends RuntimeException{
    public ClientConflictException(){}

    public ClientConflictException(String message){
        super(message);
    }
}
