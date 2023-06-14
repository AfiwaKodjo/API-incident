package gestion.incident.incident.exception;

public class AgenceConflictException extends RuntimeException{

    public AgenceConflictException(){}

    public AgenceConflictException(String message){
        super(message);
    }
}
