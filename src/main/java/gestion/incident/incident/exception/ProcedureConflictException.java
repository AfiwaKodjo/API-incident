package gestion.incident.incident.exception;

public class ProcedureConflictException extends RuntimeException{
    public ProcedureConflictException(){}
    public ProcedureConflictException(String message){
        super(message);
    }
}
