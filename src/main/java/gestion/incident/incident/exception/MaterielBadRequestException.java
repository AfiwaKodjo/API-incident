package gestion.incident.incident.exception;

public class MaterielBadRequestException extends RuntimeException{
    public MaterielBadRequestException(){}
    public MaterielBadRequestException(String message){
        super(message);
    }
}
