package gestion.incident.incident.exception;

public class Mouvement_materielNotFoundException extends RuntimeException{
    public Mouvement_materielNotFoundException(){}
    public Mouvement_materielNotFoundException(String message){
        super(message);
    }
}
