package gestion.incident.incident.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public enum Permission {
    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),

    TECHNICIEN_READ("technicien:read"),
    TECHNICIEN_UPDATE("technicien:update"),
    TECHNICIEN_CREATE("technicien:create"),
    TECHNICIEN_DELETE("technicien:delete"),

    DIRECTEUR_READ("directeur:read"),
    DIRECTEUR_UPDATE("directeur:update"),
    DIRECTEUR_CREATE("directeur:create"),
    DIRECTEUR_DELETE("directeur:delete"),

    RESPONSABLE_READ("responsable:read"),
    RESPONSABLE_UPDATE("responsable:update"),
    RESPONSABLE_CREATE("responsable:create"),
    RESPONSABLE_DELETE("responsable:delete");


    @Getter
    private final String permission;
}
