package gestion.incident.incident.exception;

public class ProcedureNotFoundException extends RuntimeException{
    public ProcedureNotFoundException(){}
    public ProcedureNotFoundException(String message){
        super(message);
    }
}
