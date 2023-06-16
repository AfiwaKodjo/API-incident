package gestion.incident.incident.exception;

public class MaterielConflictException extends RuntimeException{
    public MaterielConflictException(){}
    public MaterielConflictException(String message){
        super(message);
    }
}
