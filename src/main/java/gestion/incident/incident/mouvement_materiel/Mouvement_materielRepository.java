package gestion.incident.incident.mouvement_materiel;

import gestion.incident.incident.materiel.Materiel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Mouvement_materielRepository extends JpaRepository<Mouvement_materiel, Long> {
    //Optional<Mouvement_materiel>findByLibelleMouvement_materiel(String libelleMouvement_materiel);
}
