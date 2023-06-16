package gestion.incident.incident.exception;

public class MaterielNotFoundException extends RuntimeException{
    public MaterielNotFoundException(){}
    public MaterielNotFoundException(String message){
        super(message);
    }
}
