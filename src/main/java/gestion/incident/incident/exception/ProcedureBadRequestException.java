package gestion.incident.incident.exception;

public class ProcedureBadRequestException extends RuntimeException{
    public ProcedureBadRequestException(){}
    public ProcedureBadRequestException(String message){
        super(message);
    }
}
