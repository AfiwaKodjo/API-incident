package gestion.incident.incident.enumeration;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static gestion.incident.incident.enumeration.Permission.*;

@RequiredArgsConstructor
public enum MesRoles {
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_CREATE,
            ADMIN_DELETE

    )),
    TECHNICIEN(Set.of(
            TECHNICIEN_READ,
            TECHNICIEN_UPDATE,
            TECHNICIEN_CREATE,
            TECHNICIEN_DELETE

    )),
    RESPONSABLE(Set.of(
            RESPONSABLE_READ,
            RESPONSABLE_UPDATE,
            RESPONSABLE_CREATE,
            RESPONSABLE_DELETE

    )),
    DIRECTEUR(Set.of(
            DIRECTEUR_READ,
            DIRECTEUR_UPDATE,
            DIRECTEUR_CREATE,
            DIRECTEUR_DELETE
    ));
    @Getter
    private final Set <Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission ->new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
